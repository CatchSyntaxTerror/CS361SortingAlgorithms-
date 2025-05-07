# cs361-project
Youssef Amin, Finn Ellis, Nicholas Chacon

## benchmark.sh
Benchmarks algorithms.

To run:
```benchmark.sh {ints|doubles} [start] [end]```

Where `start` and `end` refer to the range of data set size.

## Benchmarker.jar
Arguments:

`-generate <start> <end>`: Generate data files of size $2^`<start>`$ to 
$2^`<end>`$

`-benchmark <start> <end> <trials> [-V]`: Use generated data files to 
benchmark in range for `<trials>`. Prints out CSV of benchmark.
    Optional `-V`: verbose mode

`-benchsingle <algorithm> <dataset name>`: Run a single benchmark with 
specified algorithm acronym (MS3-3 Merge Sort, QHS-Quad Heap Sort, 
RQS-Random Quick Sort, TS-Tim Sort) on specified data set name (e.g. "ints_20.
txt")

Both assume data is stored in a "data" folder in the working directory.