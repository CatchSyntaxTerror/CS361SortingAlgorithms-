# cs361-project
Youssef Amin, Finn Ellis, Nicholas Chacon

## Example Using the Benchmarker
1. Make a "Data" directory in your working directory
2. Generate data files using `java -jar Benchmarker.jar -generate 20 30` (will generate in the Data folder)
3. Run `benchmark.sh ints 20` to benchmark on the 2^20 size int dataset. Provide an end range to benchmark on more.

## benchmark.sh
Benchmarks algorithms.

To run:
```benchmark.sh {ints|doubles} [start] [end]```

Where `start` and `end` refer to the range of data set size.

## Benchmarker.jar
Arguments:

`-generate <start> <end>`: Generate data files of size $2^`<start>`$ to 
$2^`<end>`$

`-benchsingle <algorithm> <dataset name>`: Run a single benchmark with 
specified algorithm acronym (MS3-3 Merge Sort, QHS-Quad Heap Sort, 
RQS-Random Quick Sort, TS-Tim Sort) on specified data set name (e.g. "ints_20.
txt")

Both assume data is stored in a "data" folder in the working directory.
