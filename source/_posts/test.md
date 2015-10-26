title: "Markdown简单语法"
date: 2015-04-17 08:46:06
tags:
---

## 第一章

1. ***MarkeDown简介***

	 	1.1 MarkeDown简介
			Markdown是一种可以使用普通文本编辑器编写的标记语
			言，通过类似HTML的标记语法，它可以使普通文本内容
			具有一定的格式。

2.  ***markdown语法***

		2.2 基本符号
			1. *,-,+ 3个符号效果都一样，这3个符号被称为 Markdown符号
            2. 空白行表示另起一个段落
            3. `是表示inline代码，tab是用来标记 代码段，分别对应html的code，pre标签
        2.3 换行
            1. 单一段落( <p>) 用一个空白行
            2. 连续两个空格 会变成一个 <br>
            3. 连续3个符号，然后是空行，表示 hr横线
        2.4 标题
            1. 生成h1--h6,在文字前面加上 1--6个# 来实现
			2. 文字加粗是通过 文字左右各两个符号
		2.5 链接
			1. 直接写 [锚文本](url "可选的title")
			2. 引用 先定义 [ref_name]:url，然后在需要写入url的地方， 这样使用[锚文本][ref_name]，通常的
			3. ref_name一般用数字表示，这样显得专业
	    2.6 插入图片
			1. 一行表示:![alt_text](url "可选的title")
			2. 引用表示法: ![alt_text][id],预先定义 [id]:url "可选title"
			3. 直接使用<img>标签，这样可以指定图片的大小尺寸
		2.7 特殊符号
			1. 用\来转义，表示文本中的markdown符号
			2. 可以在文本种直接使用html标签，但是要注意在使用的时候，前后加上空行
			3. 文本前后各加一个符号，表示斜体
						
3.  ***markdown语法参考链接***

	[链接一(简书)](http://www.jianshu.com/p/1e402922ee32/)

	[链接二(博客园)](http://www.cnblogs.com/hnrainll/p/3514637.html)
			

			
			
	
	 