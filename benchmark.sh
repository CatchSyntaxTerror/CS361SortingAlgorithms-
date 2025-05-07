#!/bin/bash
# Benchmarker bash script to (hopefully) get around java memory inefficiency

### INITIAL DEFINITIONS
num_trials=5 # You can change this number as needed
out_dir="benchruns/"
range_start=20
range_end=30
algorithms=("MS3" "QHS" "RQS" "TS")

# Data category argument
data_category="$1"
case "$1" in "ints" | "doubles")
    ;;
  *)
    echo "Error: Invalid argument '$1'. Allowed values are: ints, doubles"
    exit 1 # Exit with an error code
    ;;
esac

# Range start argument
if [ -n "$2" ]; then
  if [[ "$2" =~ ^[0-9]+$ ]]; then
    range_start="$2"
  else
    echo "Error: Range start must be an int"
    exit 1
  fi
fi

# Range end argument
if [ -n "$3" ]; then
  if [[ "$3" =~ ^[0-9]+$ ]]; then
    range_end="$3"
  else
    echo "Error: Range end must be an int"
    exit 1
  fi
fi

# Prepare CSV header for each file
header="DataSet"
for ((i=1; i<=num_trials; i++))
do
  header="${header},Trial${i}"
done

### BEGIN BENCHMARKING
# Print initial info
java -jar -Xmx28G -Xms28G jars/Benchmarker.jar INFO

# Benchmark each algorithm
for algorithm in "${algorithms[@]}";
do
  echo "Running ${algorithm}"

  output_file="${out_dir}benchrun_${algorithm}_${data_category}.csv"
  echo "${header}" > "$output_file"

  for (( x=range_start; x<=range_end; x++ ))
  do
    input_file="${data_category}_${x}.txt"
    # Xmx28G specifies maximum memory available to Java
    # Xms specifies initial size
    command="java -jar -Xmx28G -Xms28G jars/Benchmarker.jar -benchsingle ${algorithm} ${input_file}"

    echo "Running benchmark for ${input_file} with ${num_trials} trials..."

    output="${data_category}_${x}"
    for ((i=1; i<=num_trials; i++))
    do
      output="${output},$(${command})"
    done
    csv_line="${input_file},${output}"
    echo "${csv_line}" >> "$output_file"
  done

  echo "Saved to ${output_file}"
done

echo "Benchmarking complete."