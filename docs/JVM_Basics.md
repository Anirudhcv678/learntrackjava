# JVM Basics

## What is JDK, JRE, JVM?
- **JDK (Java Development Kit)**: A full-featured software development kit for Java, including JRE, compilers (javac), and tools (like javadoc, jdb).
- **JRE (Java Runtime Environment)**: A package of everything necessary to run a compiled Java program, including the Java Virtual Machine (JVM) and the Java Class Library.
- **JVM (Java Virtual Machine)**: An abstract computing machine that enables a computer to run a Java program. It interprets compiled Java binary code (bytecode) for a computer's hardware platform to perform a Java program's instructions.

## What is bytecode?
Bytecode is the compiled format for Java programs. When you write standard Java code and compile it with `javac`, the compiler outputs a `.class` file containing bytecode. This bytecode is a set of instructions that the JVM executes.

## "Write once, run anywhere"
"Write once, run anywhere" (WORA) is a slogan created by Sun Microsystems to illustrate the cross-platform benefits of the Java language. Because Java programs are compiled into bytecode rather than machine-specific code, the compiled program can be executed on any device or operating system that has a JVM, without needing to be recompiled for that specific architecture.
