# Chat Spring

## 概要
このプロジェクトは、Spring Bootを使用したシンプルなチャットアプリケーションです。

## 必要条件
- JDK 11以上
- Gradle 6.8以上
- MySQL

## インストール方法

### リポジトリのクローン
```bash
git clone https://github.com/daiki-takeuchi/chat_spring.git
cd chat_spring
```

### データベースの設定
.envにデータベースの接続情報を設定します。
```.env
MYSQL_RANDOM_ROOT_PASSWORD=yes
MYSQL_DATABASE=dummy
MYSQL_USER=dummy
MYSQL_PASSWORD=dummy
```

### 依存関係のインストール
```bash
./gradlew build
```

## 実行方法
```bash
./gradlew bootRun
```
ブラウザで http://localhost:8080 にアクセスしてください。

## 使用方法
1. ブラウザを開き、http://localhost:8080 にアクセスします。
2. チャットルームに参加し、メッセージを送信できます。


==============

[![Build Status](https://travis-ci.org/daiki-takeuchi/chat_spring.svg?branch=master)](https://travis-ci.org/daiki-takeuchi/chat_spring)
[![codecov](https://codecov.io/gh/daiki-takeuchi/chat_spring/branch/master/graph/badge.svg)](https://codecov.io/gh/daiki-takeuchi/chat_spring)

