---
title: 更新内容
tags: 更新说明,小书匠
slug: storywriter/upgrade_log
grammar_mindmap: true
renderNumberedHeading: true
grammar_code: true
grammar_decorate: true
grammar_mathjax: true
---


[toc!?direction=lr]

# 小书匠收费

## 收费与不收费的区别

### 收费

#### 收费项目
1. pdf 定制化导出(pdf封面，水印，加密等)
2. 支持在线更新，优先使用新功能
3. 配置数据同步
4. 自定义数据中心

#### 收费价格

1. 详细价格可以查看该[地址](http://soft.xiaoshujiang.com/price/)
 
### 不收费

1. 免费又实用的功能太多了，不知道重点写什么好，自己到 http://soft.xiaoshujiang.com/feature.html 这里看吧.....

## 其他

http://soft.xiaoshujiang.com/price.html

___

# 升级日志

## 注

如果您从较老版本的小书匠升级，内置数据库会有不兼容问题，建议在升级前进行数据导出备份，或者数据库文件备份，防止升级失败。

数据库文件路径

```
Windows: %LOCALAPPDATA%/storywriter/
Mac: ~/Library/Application Support/storywriter/
Linux: ~/.config/storywriter
```

## 7.9.5

### 7.9.5 新功能

1. 添加日历过滤器列表功能(按创建时间过滤文件)
2. 链接关系图添加对标签的支持 #1246

### 7.9.5 修改

1. 修改历史记录列表显示方式 #1247
2. 在使用过滤器状态下，不需要显示归档同步状态图标

## 7.9.4

### 7.9.4 修改

1. 修复修改属性造成文件无法保存的问题 #1243

## 7.9.3

### 7.9.3 新功能

1. 添加文章之间链接功能 [详细说明](http://soft.xiaoshujiang.com/docs/tutorial/internal_link)
2. 添加链接关系图 #1196
3. 添加修改文件属性命令
4. 文件列表添加右键修改属性功能
5. 分类过滤器实现支持起始于关系。(在添加分类过滤器时，如果需要起始于关系，在过滤值前添加 `xsjStartsWith##`) #1241

### 7.9.3 修改


1. 在最新版本的浏览器内， 第三方存储搜索分页条无法显示的问题
2. 兼容 smms 代码更新 #1236
3. 修复 CodeMirror 编辑器搜索框在切换成其他内置编辑器时,内存没有正常释放的问题
4. 导入的文件，自动更新相关元数据属性。

## 7.9.2

### 7.9.2 修改

1. 调整标题输入框修改样式
2. 修复没有正确创建更新手册问题
3. 不在过滤器状态下的文件显示成半透明效果
4. 过滤器列表状态没有正确重置

## 7.9.1  <!-- {data-mindmap-range-node=true} -->

### 7.9.1 新功能 <!-- {data-mindmap-name="小书匠|新功能" data-mindmap-important-node=true} -->

1. 添加过滤器，过滤文件列表功能。详细使用可以参考[这里](http://soft.xiaoshujiang.com/docs/tutorial/filter)(注：会员功能)
2. 文章添加 category, flag 元数据支持

### 7.9.1 修改 <!-- {data-mindmap-important-path=true} -->

1. 优化应用启动逻辑

## 7.9.0

[小书匠价格及邀请活动奖励调整说明](http://soft.xiaoshujiang.com/blog/price_change_plan)

### 7.9.0 新功能

1. 添加大纲，思维导图大纲隐藏结点属性

```
data-toc-hidden: 大纲结点隐藏
data-mindmap-hidden: 大纲思维导图分支隐藏
```
2. 添加 docker 为知企业版本支持
3. 添加 smms v2 版本图床 #1219 (感谢 @jackytsu 提供的代码)

### 7.9.0 修改

1. 发布博客支持 markdown 原文功能
2. 部份主题光标样式调整 #1216
3. 客户端启动时居中问题 #1207

## 7.8.8

### 7.8.8 新功能

1. 添加 toggleVimMode 和 toggleEmacsMode 命令
2. 大纲思维导图结点描述可以通过属性语法进行配置

```
data-mindmap-name: 可以重新定义结点的名称
data-mindmap-id: 可以定义结点的别名
data-mindmap-important-node: 定义是否重点结点
data-mindmap-important-path: 定义是否重点分支
data-mindmap-range-node: 定义是否范围结点
```


```?title=示例

# 小书匠 <!-- {data-mindmap-name="小|书|匠" data-mindmap-range-node=true data-mindmap-important-node=true} -->

```

### 7.8.8 修改

1. 修复 ace 编辑器部分快捷键在编辑区无法使用问题 #1204


## 7.8.7

### 7.8.7 新功能

1. 添加快捷键自定义功能，详细操作可以参考文档 《[小书匠自定义快捷键说明](http://soft.xiaoshujiang.com/docs/tutorial/shortcuts)》 和 《[小书匠命令说明](http://soft.xiaoshujiang.com/docs/tutorial/commands)》

### 7.8.7 修改

1. "继费" 改成 "续费" #1199
2. 窗口最大化之后会改变页面布局 #1200
3. 连续插入多个代码片段时会合并到一个代码片段里 #1201
4. 多行待办列表快捷键命令逻辑调整 #1197

## 7.8.6

### 7.8.6 修改

1. 思维导图在有注释时，宽度大小调整
2. 思维导图文字样式调整
3. 列表快捷键兼容多行文字处理 #1193

## 7.8.5

### 7.8.5 新功能

1. 添加思维脑图关联，范围，注释功能。详细使用可以参考[这里](http://soft.xiaoshujiang.com/blog/mindmap/extense)

## 7.8.4


### 7.8.4 修改

1. 修复大纲点击失效问题 #1280

## 7.8.3

### 7.8.3 新功能


1. 思维脑图添加重点结点（importantNodes） 和重点分支(importantPaths)功能

一个`!`开头，表示重点结点，也可以用 importantNodes 参数指定第几行为重点结点，多个重点结点以逗号分隔
二个`!`开头，表示重点分支，也可以用 importantPaths 参数指定第几行所在的结点对应的分支为重点分支，多条重点分支以逗号分隔

注： 根结点没有重点结点和重点分支功能

```mindmap!?direction=TB&importantPaths=7,8
小书匠|主要功能
  各种强大的|第三方存储
    ! 印象笔记
    evernote
    为知笔记
    github
    gitlab
    gitee
    本地存储
  丰\n富\n的\n导\n出\n功\n能
    !! pdf 导出
      页眉页脚
      自定义水印
      加密
      封面
      目录
    !!! word 导出
    zip 导出
    epub 导出
```

### 7.8.3 修改

1. 将导出 docx 默认设置为 a4 纸张大小
2. 打印或者客户端本地导出 pdf 大纲思维脑图无法正常点击问题
3. 预览样式调整
4. 对 firefox 浏览器做兼容性调整
5. 修复相册模式下，esc ，方向键等按键无法正常使用的问题
6. 调整图片查看相册模式下，图片放大缩小操作
7. 系统默认模板自动对标题添加编号
8. web 版本非会员也可以使用思维脑图
9. 修复绑定同步配置信息的问题
10. 修复 smms 图床错误信息没有正确提示的问题
11. 修复 gif 图片迁移转换成本地图片时，扩展名错误的问题

## 7.8.2

### 7.8.2 新功能

1. 表格组件，绘图组件添加标题显示支持(需要在`设置>预览`里打开相关选项)
2. 标签可以在右上角进行显示和修改

### 7.8.2 修改

1. 修复客户端 pdf 导出和打印大纲链接无法点击问题
2. 相册模式样式调整
3. 修复带目录脚本的 html 导出，定位不准确问题 #1176
4. 修复客户端 pdf 导出和打印带图标不能正常显示的问题

## 7.8.1

### 7.8.1 修改


1. 生成静态目录的序号问题？ #1171
2. DrawIO无法插入图片 #1174
3. 剪切版粘贴图片，图床迁移时，保证生成的图片名称唯一
4. github 图床默认前缀地址修改为 https://raw.githubusercontent.com/{username}/{reponame}/{branchname}/
5. 同步状态更新提示不自动消失的问题

## 7.8.0 

### 7.8.0 新功能

1. 思维导图实现换行功能(通过`,`,`\n`,`。`,`，`,`|`进行换行)

```mindmap!?direction=TB
小书匠|主要功能
  各种强大的|第三方存储
    印象笔记
    evernote
    为知笔记
    github
    gitlab
    gitee
    本地存储
  丰\n富\n的\n导\n出\n功\n能
    pdf 导出
      页眉页脚
      自定义水印
      加密
      封面
      目录
    word 导出
    zip 导出
    epub 导出
```

2. codemirror 编辑器打字机滚动效果可以调整固定位置(编辑器左侧边有一个可以拖动的调整图标<kbd><i class="fas fa-thumbtack"></i></kbd>)


### 7.8.0 修改

1. 发布到博客时，删除多余的 script 标签
2. 修正 wordpress 发布时间问题 #1153
3. 跟进为知笔记 api 接口调整，解决为知附件不能正常保存问题
4. 修复 url 里包含特殊符号，会导致图床迁移失败的问题
5. 修复客户端导出  pdf 或者 客户端 打印时，网络图片无法正常显示问题
6. codemirror 编辑器模式下，用户输入文字期间，同时打开打字机滚动和编辑预览同步滚动功能，编辑光标会跳动的问题
7. 修复 ppt模式下图片无法显示 #1162


# 其他

[小书匠更新手册](storywriter/upgrade_log)
[小书匠语法手册](storywriter/grammar)
[小书匠使用手册](storywriter/tutorial)
