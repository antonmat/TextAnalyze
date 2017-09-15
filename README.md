
OVERVIEW
The test involves writing simple Vert.x REST service that compiles a list of words and performs
simple text analysis on them.
● The program can be implemented in any technology supported by Vert.x but Java is
recommended. See Appendix A for an example project.
● The program shall be published on GitHub with an open source license of your choosing.
Please make sure to apply an open source license and add the relevant files.
● A README file must be included that will detail how the program should be compiled (if
needed) and any details needed to be able to run the program (such as required libraries
or tools).

REQUIREMENTS
1. The program will expose an HTTP REST interface that responds to POST requests on the
URL /analyze.
2. When an HTTP client POSTs a JSON object with the property “text”, the server will
compare the content of the provided “text” field to the list of words previously provided
through the same API, and return a JSON response containing an object with two fields:
a. The field “value” will contain the word closest to the word provided in the
request in term of total character value, where character values are listed as a=1,
b=2 and so on.
b. The field “lexical” will contain the word closest to the word provided in the
request in term of lexical closeness - i.e. that word that sorts lexically closest to the
provided request.

3. The server will store the word provided in the request locally to compare against future
requests.
4. If no words are found to match against (as in the first request), the server will return null
for both response fields.

To run:

```
mvn org.codehaus.mojo:exec-maven-plugin:exec -Dexec.executable=java \
	-Dexec.args="-cp %classpath io.vertx.core.Launcher run test.project1.Server"
```

To test:

```
using postman send a http post request to http://localhost:90/analyze header should cotain Key:Content-Type value:"application/json"
the post body should contain a valid json with a field named "text" and it's value.
Example:
 {
	"text" : "test"
}
```
