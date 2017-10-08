Deploy RESTfulExample
  http://localhost:8080/RESTfulExample/rest/hello/mkyong 

Just run build (not test is run)
	mvn -Dmaven.test.skip=true clean install

Just run test
	mvn -Dtest=TsFrameApplicationTests test 