# ACV-Loops-Repository
This is a curated set of loops which illustrate loop characteristics that can lead to algorithmic complexity vulnerabilities (ACV)

The set was curated as part of research done for the STAC program at Iowa State University (More information on STAC - http://www.darpa.mil/program/space-time-analysis-for-cybersecurity)

Notes on accessing and understanding the repository :
- This is not the complete code. It compiles but executing it won't do anything meaningful.
- The only code snippets, apart from loop body, retained are those which must be analyzed in order to flag a loop as a potentially ACV causing loop and any additional stub to make it compile.
- Unless mentioned, assume that attacker can control the parameters to the methods containing loops.
