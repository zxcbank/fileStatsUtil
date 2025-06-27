# File Content Filtering Utility

## Overview
This utility processes input files containing mixed integer, float, and string data (separated by newlines). It categorizes the data into separate output files and provides statistics about the processed content. The utility is implemented in Java and handles various error conditions gracefully.

## Features
- **Data Filtering**: Separates integers, floats, and strings into distinct files
- **Output Customization**:
  - `-o` : Specify output directory (default: current directory)
  - `-p` : Add prefix to output filenames
- **File Modes**:
  - Overwrite existing files (default)
  - `-a` : Append to existing files
- **Statistics Reporting**:
  - `-s` : Short statistics (count per data type)
  - `-f` : Full statistics (min/max values, sums, averages)

## Command Syntax
```bash
java -jar util.jar [OPTIONS] FILE1 FILE2 ...
```

## Options
| Option | Description                                      | Default Value       |
|--------|--------------------------------------------------|---------------------|
| `-o`   | Output directory path                            | Current directory   |
| `-p`   | Prefix for output filenames                      | No prefix           |
| `-a`   | Append mode (don't overwrite existing files)     | Overwrite mode      |
| `-s`   | Show short statistics (count per type)           | No statistics       |
| `-f`   | Show full statistics (detailed metrics per type) | No statistics       |

## Output Files
| Data Type | Default Filename    | Example with `-o /out -p result_` |
|-----------|---------------------|-----------------------------------|
| Integers  | `integers.txt`      | `/out/result_integers.txt`        |
| Floats    | `floats.txt`        | `/out/result_floats.txt`          |
| Strings   | `strings.txt`       | `/out/result_strings.txt`         |

*Files are only created if their corresponding data type exists in input*

## Example

**Input Files**  
*in1.txt*:
```
Lorem ipsum dolor sit amet
45
Пример
3.1415
consectetur adipiscing
-0.001
тестовое задание
100500
```

*in2.txt*:
```
Нормальная форма числа с плавающей запятой
1.528535047E-25
Long
1234567890123456789
```

**Command**:
```bash
java -jar util.jar -s -a -p sample- in1.txt in2.txt
```

**Output Files**  
*sample-integers.txt*:
```
45
1234567890123456789
100500
```

*sample-floats.txt*:
```
1.528535047E-25
3.1415
-0.001
```

*sample-strings.txt*:
```
Lorem ipsum dolor sit amet
Нормальная форма числа с плавающей запятой
Пример
Long
consectetur adipiscing
тестовое задание
```

**Console Output (Statistics)**:
```
Integers: 3 items
Floats: 3 items
Strings: 6 items
```

## Error Handling
- Continues processing after recoverable errors
- Provides descriptive error messages
- Handles file access/permission issues
- Skips invalid data lines while processing
- Validates numerical formats (supports scientific notation)

## Implementation Details

### Data Type Detection Logic
```java
    public static Class<?> detectType(String value) throws IllegalArgumentException{
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Пустая строка");
        }

        if (value.matches("-?\\d+")) {
            return Integer.class;
        }

        if (value.matches("-?\\d+\\.\\d+")) {
            return Float.class;
        }

        if (value.matches("\\p{L}+")) {
            return String.class;
        }

        throw new IllegalArgumentException("Wrong String: " + value);
    }
```

### Statistics Collection
| Data Type | Full Statistics Metrics          |
|-----------|----------------------------------|
| Integers  | Count, Min, Max, Sum, Average    |
| Floats    | Count, Min, Max, Sum, Average    |
| Strings   | Count, Shortest Length, Longest Length |

## Build and Execution

### Requirements
- Java 21 or higher
- Maven 4.0.0+

### Build Instructions
```bash
mvn clean package
```

### Run Instructions
```bash
java -jar target/util.jar [OPTIONS] FILE1 FILE2...
```

### Dependencies
- JCommander

## Design Principles
- functional-style
- Resource-safe file handling (on IO and Arg exceptions)
- multi-threading (in nearest future)
- tests (in nearest future)
- intall-guide (in nearest future)

## Statistics Examples

**Short Statistics** (`-s` option):
```
[STATISTICS]
Integers: 3 items
Floats: 3 items
Strings: 6 items
```

**Full Statistics** (`-f` option):
```
[STATISTICS]
INTEGERS:
  Count: 3
  Min: 45
  Max: 100500
  Sum: 112500
  Average: 37500.00

FLOATS:
  Count: 3
  Min: -0.001
  Max: 3.1415
  Sum: 3.1414999999999997
  Average: 1.0471666666666665

STRINGS:
  Count: 6
  Shortest: 4 characters
  Longest: 43 characters
```
