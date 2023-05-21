# Code analysis
## ing-app-spark Maven Webapp 
#### Version 1.0 

**By: default**

*Date: 2023-05-21*

## Introduction
This document contains results of the code analysis of ing-app-spark Maven Webapp



## Configuration

- Quality Profiles
    - Names: Sonar way [Java]; Sonar way [XML]; 
    - Files: AYg_y3J1mdQ7xc402lai.json; AYg_y3O6mdQ7xc402lkK.json; 


 - Quality Gate
    - Name: Sonar way
    - File: Sonar way.xml

## Synthesis

### Analysis Status

Reliability | Security | Security Review | Maintainability |
:---:|:---:|:---:|:---:
A | A | A | A |

### Quality gate status

| Quality Gate Status | OK |
|-|-|

Metric|Value
---|---
Reliability Rating on New Code|OK
Security Rating on New Code|OK
Maintainability Rating on New Code|OK


### Metrics

Coverage | Duplications | Comment density | Median number of lines of code per file | Adherence to coding standard |
:---:|:---:|:---:|:---:|:---:
0.0 % | 0.0 % | 0.0 % | 36.5 | 100.0 %

### Tests

Total | Success Rate | Skipped | Errors | Failures |
:---:|:---:|:---:|:---:|:---:
4 | 100.0 % | 0 | 0 | 0

### Detailed technical debt

Reliability|Security|Maintainability|Total
---|---|---|---
-|-|0d 0h 20min|0d 0h 20min


### Metrics Range

\ | Cyclomatic Complexity | Cognitive Complexity | Lines of code per file | Coverage | Comment density (%) | Duplication (%)
:---|:---:|:---:|:---:|:---:|:---:|:---:
Min | 0.0 | 0.0 | 4.0 | 0.0 | 0.0 | 0.0
Max | 59.0 | 27.0 | 363.0 | 0.0 | 0.0 | 0.0

### Volume

Language|Number
---|---
Java|363
XML|134
Total|497


## Issues

### Issues count by severity and types

Type / Severity|INFO|MINOR|MAJOR|CRITICAL|BLOCKER
---|---|---|---|---|---
BUG|0|0|0|0|0
VULNERABILITY|0|0|0|0|0
CODE_SMELL|0|0|1|0|0


### Issues List

Name|Description|Type|Severity|Number
---|---|---|---|---
Generic exceptions should never be thrown|Using such generic exceptions as Error, RuntimeException, Throwable, and Exception prevents <br /> calling methods from handling true, system-generated exceptions differently than application-generated errors. <br /> Noncompliant Code Example <br />  <br /> public void foo(String bar) throws Throwable {  // Noncompliant <br />   throw new RuntimeException("My Message");     // Noncompliant <br /> } <br />  <br /> Compliant Solution <br />  <br /> public void foo(String bar) { <br />   throw new MyOwnRuntimeException("My Message"); <br /> } <br />  <br /> Exceptions <br /> Generic exceptions in the signatures of overriding methods are ignored, because overriding method has to follow signature of the throw declaration <br /> in the superclass. The issue will be raised on superclass declaration of the method (or won’t be raised at all if superclass is not part of the <br /> analysis). <br />  <br /> @Override <br /> public void myMethod() throws Exception {...} <br />  <br /> Generic exceptions are also ignored in the signatures of methods that make calls to methods that throw generic exceptions. <br />  <br /> public void myOtherMethod throws Exception { <br />   doTheThing();  // this method throws Exception <br /> } <br />  <br /> See <br />  <br />    MITRE, CWE-397 - Declaration of Throws for Generic Exception  <br />    CERT, ERR07-J. - Do not throw RuntimeException, Exception, or Throwable  <br /> |CODE_SMELL|MAJOR|1


## Security Hotspots

### Security hotspots count by category and priority

Category / Priority|LOW|MEDIUM|HIGH
---|---|---|---
LDAP Injection|0|0|0
Object Injection|0|0|0
Server-Side Request Forgery (SSRF)|0|0|0
XML External Entity (XXE)|0|0|0
Insecure Configuration|0|0|0
XPath Injection|0|0|0
Authentication|0|0|0
Weak Cryptography|0|0|0
Denial of Service (DoS)|0|0|0
Log Injection|0|0|0
Cross-Site Request Forgery (CSRF)|0|0|0
Open Redirect|0|0|0
Permission|0|0|0
SQL Injection|0|0|0
Encryption of Sensitive Data|0|0|0
Traceability|0|0|0
Buffer Overflow|0|0|0
File Manipulation|0|0|0
Code Injection (RCE)|0|0|0
Cross-Site Scripting (XSS)|0|0|0
Command Injection|0|0|0
Path Traversal Injection|0|0|0
HTTP Response Splitting|0|0|0
Others|0|0|0


### Security hotspots

