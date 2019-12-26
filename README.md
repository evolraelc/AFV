---
title:  AFV项目文档
tags: Eyepetizer,Android,Retrofit2+RxJava
slug: storywriter/upgrade_log
grammar_mindmap: true
renderNumberedHeading: true
grammar_code: true
grammar_decorate: true
grammar_mathjax: true
---



## 项目简介
   项目实现一款在线小视频观看APP，视频来源于Eyepetizer。

___

## 项目实现功能

- [x] “主页” 界面实现每日视频推荐功能，提供一系列推荐视频
- [x] “发现”界面提供视频类别的视频推荐
- [x] “热门”界面提供根据排行（周排行、列排行）排列的视频
- [x] 提供关键词搜索功能
- [x] 视频播放功能
- [x] 观看记录查询
- [ ] 视频缓存功能
- [ ] “发现”界面点击类别显示更多信息

___



## 项目开发

### 开源库使用

 - Retrofit：网络请求
 - RxJava：异步操作
 - Glide : 视频封面图片加载
 - Gson ：Json数据解析
 - RxDownload：视频缓存下载
 - Anko Commons：Intent、log的使用
 - GSYVideoPlayer：视频播放器

### 项目框架

使用GoogleMVP架构：Model+View+Presenter+Contract

___


## 项目截图



# 其他

[小书匠更新手册](storywriter/upgrade_log)
[小书匠语法手册](storywriter/grammar)
[小书匠使用手册](storywriter/tutorial)
