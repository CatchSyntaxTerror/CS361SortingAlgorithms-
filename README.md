# cs361-project
Youssef Amin, Finn Ellis, Nicholas Chacon

## Benchmarker.jar
Arguments:

`-generate <start> <end>`: Generate data files of size $2^`<start>`$ to 
$2^`<end>`$

`-benchmark <start> <end> <trials> [-V]`: Use generated data files to 
benchmark in range for `<trials>`. Prints out CSV of benchmark.
    Optional `-V`: verbose mode

Both assume data is stored in a "data" folder in the working directory.