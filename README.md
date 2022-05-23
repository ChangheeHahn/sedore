# An Implementation of Delegatable Order-Revealing Encryption for Reliable Cross-Database Query (SEDORE)
This is an implementation of SEDORE, a secure order-revealing encryption scheme with resilience to unauthorized queries across database described here: (link to be posted)

This implementation is a research prototype built for micro-benchmarking purposes, and is not intended to be used in production-level code as it has not been carefully analyzed for potential security flaws.

Author: Changhee Hahn, Seoul National University of Science and Techynology

Contact Changhee Hahn for questions about the code: chahn@seoultech.ac.kr

## Prerequisites
Make sure you have the following installed:
> The Java Pairing-Based Cryptography Library ([JPBC](http://http://gas.dia.unisa.it/projects/jpbc))

## Installation
1. Download jpbc_2_0_0.zip (or jpbc_2_0_0.tar.gz depending on your OS).
2. Unzip it and locate the jar directory (jpbc-2.0.0\jars).
3. Add these external jar files in the directory into your Java build path.
4. Locate the curves directory (jpbc-2.0.0\params\curves).
5. Add curve files to use for pairings in your project directory.

## Running a Test
Run Main.java in which two numerical data (i.e., m1 and m2) are encrypted and then tested.
