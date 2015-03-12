---
layout: article
title: "JavaFX 8 教程 （中文）"
date: 2014-10-08 00:00
updated: 2014-10-20 00:00
slug: javafx-8-tutorial/zh-cn
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-zh-cn.md
description: "这 7 部分组成的教程将介绍一个用 JavaFX 编写的联系人程的设计，开发与部署的过程。"
image: /assets/library/javafx-8-tutorial/addressapp.png
published: true
prettify: true
comments: false
sidebars:
- header: "系列文章"
  body:
  - text: "简介"
    link: /library/javafx-8-tutorial/zh-cn/
    paging: 简介
    active: true
  - text: "第一部分：Scene Builder"
    link: /library/javafx-8-tutorial/zh-cn/part1/
    paging: 1
  - text: "第二部分：Model 和 TableView"
    link: /library/javafx-8-tutorial/zh-cn/part2/
    paging: 2
  - text: "第三部分：与用户的交互"
    link: /library/javafx-8-tutorial/zh-cn/part3/
    paging: 3
  - text: "第四部分：CSS 样式"
    link: /library/javafx-8-tutorial/zh-cn/part4/
    paging: 4
  - text: "第五部分：将数据用 XML 格式存储"
    link: /library/javafx-8-tutorial/zh-cn/part5/
    paging: 5
  - text: "第六部分：统计图"
    link: /library/javafx-8-tutorial/zh-cn/part6/
    paging: 6
  - text: "第七部分：部署"
    link: /library/javafx-8-tutorial/zh-cn/part7/
    paging: 7
- header: 语言
  languages: true
  body:
  - text: English
    link: /library/javafx-8-tutorial/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/
    icon-css: fa fa-fw fa-globe
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Русский
    link: /library/javafx-8-tutorial/ru/
    icon-css: fa fa-fw fa-globe
  - text: Bahasa Indonesia
    link: /library/javafx-8-tutorial/id/
    icon-css: fa fa-fw fa-globe
  - text: Français
    link: /library/javafx-8-tutorial/fr/
    icon-css: fa fa-fw fa-globe
---

早在 2012 我和我的学生们写了一个非常详细的 [JavaFX 2 系列教程](/java/javafx-2-tutorial-intro/)。世界各地的人们都已阅读了这个教程并给了非常积极的反馈。所以我决定 **为 JavaFX 8 改写 JavaFX 2 的教程** (阅读关于 JavaFX 8 的变化 [Update to JavaFX 8 - What's New](/blog/update-to-javafx-8-whats-new/)).

这个教程将指导您设计，编写并部署一个联系人应用程序。应用程序最后将会是这个样子：

![Screenshot AddressApp](/assets/library/javafx-8-tutorial/addressapp.png)


## 你将学到什么？

* 创建并启动一个 JavaFX 项目。
* 使用 Scene Builder 设计 UI 。
* 构造一个 模型 - 视图 - 控制器 (MVC) 模式的应用程序。
* 使用 `ObservableLists` 来自动更新用户界面。
* 使用 `TableView` 来响应列表中的选择。
* 创建一个 edit persons 的自定义弹出式对话框。
* 验证用户输入。
* 使用 CSS 样式化一个 JavaFX 应用程序。
* 使用 XML 保存数据。
* 在用户配置中保存最后一次打开文件的路径。
* 创建 JavaFX 的统计图表。
* 部署一个 JavaFX 到本机软件包。

**这是相当多的！** 所以，当你学习完这个教程后，你应该准备好使用 JavaFX 构建复杂的应用程序。


## 如何使用这个教程？

这有两种使用本教程两种方法：

* **最大化学习的通道：** 从头开始创建自己的 JavaFX 项目。
* **快速通道：** 导入教程部分的源代码到你的 IDE（它是一个 Eclipse 项目，但是你可以稍作修改后使用其它的 IDE 例如 NetBeans 这样的)。然后再通过教程来理解代码。

现在，我希望你觉得有趣！开始 [第一部分：Scene Builder](/library/javafx-8-tutorial/zh-cn/part1/).

<div class="alert alert-success">
  <strong><i class="fa fa-trophy"></i> Attribution:</strong> Chinese translations have been contributed by 
  <ul>
    <li><a href="https://github.com/KaiYuan-Guo" class="alert-link">KaiYuan Guo</a></li> 
    <li><a href="https://github.com/linychuo" class="alert-link">LiYongchao</a></li>
    <li><a href="https://github.com/fairjm" class="alert-link">Fairjm</a></li>
    <li><a href="https://github.com/shfhp" class="alert-link">Haiping Fan</a></li>
  </ul>
  Thank you very much!
</div>
