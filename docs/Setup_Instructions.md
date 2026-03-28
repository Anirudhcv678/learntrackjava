# Environment Setup Instructions

## Requirements
- Java Development Kit (JDK) 11 or higher.

## Setup Steps
1. Install JDK. On Mac, you can use Homebrew: `brew install openjdk`.
2. Verify installation:
   ```bash
   java -version
   javac -version
   ```

## Hello World
Create a simple `HelloWorld.java`:
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```
Compile and run:
```bash
javac HelloWorld.java
java HelloWorld
```
Output should be: `Hello World!`
