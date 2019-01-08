doc-server 文档服务

1.利用freemarker生成word文档:
（1）模板准备
a. 准备一个word文档，使用 ${param} 填充数据， 此处的param与数据源中对象需要一致
b. 将文档另存为 " Word 2003 XML 文档(.xml) "，即，模板文件
c. 使用工具编辑 模板文件，加入一些 freemarker 标签元素，比如，<#list r1list as r1></#list> 等
（2）代码处理...

2.利用easypoi生成excel文档
（1）模板准备
