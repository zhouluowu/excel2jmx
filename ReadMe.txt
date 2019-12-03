此工具针对单接口的单个参数生成对应的用例，校验是否为空、边界值、特殊字符、参数是否唯一

======excel用例模板======
isrun: 是否运行
name:用例名
path:用例路径
method:方法名，目前只支持post和get
paramtype：传参类型，看请求头的content—type字段。比如application/json，就填写json，其他为空，或者不填写。
params:接口入参
checkParam：测试的参数，
fieldType：测试参数的类型
unique：是否唯一性
isNullable：是否允许为空Y则允许为空，N不允许为空
min：最小值，不校验则不填写
max：最大值，不校验则不填写
specailList：特殊字符，不填写则代表允许任意特殊字符；填写则代表不允许输入的字符
isPre:是否前置用例
prepath:前置用例的路径
premethod：前置用例的方法
preparamtype：前置用例的传参格式
preparams：前置用例的入参
presave:前置用例需要保存的值，目前只支持jsonpath,保存的多个值用";"分隔。使用语法：result=$.data

