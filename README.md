# Hierarchical representation of a small company

### Input
The input is a file containing the list of employees in a JSON format. It is _resources/company.json_.
The Gson library is leveraged to load the content of the file in a list of employees object.

### Execution
It is a console SpringBoot application that does not require any command line argument.
To run it use the following commands:
 
* gradlew clean build

* gradlew bootRun

### Implementation details
The employee list is loaded in a tree representing the company hierarchy. The tree is then traversed 
from top to bottom to get the proper representation. 