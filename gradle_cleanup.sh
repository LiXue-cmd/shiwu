#!/bin/bash

# Gradle清理和修复脚本
# 解决Gradle缓存损坏和版本不兼容问题

echo "开始清理Gradle缓存和修复项目..."

# 项目路径
PROJECT_PATH="/Users/lilili/Documents/工作/other/shiwu"

# 检查项目是否存在
if [ ! -d "$PROJECT_PATH" ]; then
    echo "错误: 项目路径不存在: $PROJECT_PATH"
    exit 1
fi

cd "$PROJECT_PATH"

echo "1. 停止所有Gradle守护进程..."
./gradlew --stop

echo "2. 清理项目构建缓存..."
./gradlew clean

echo "3. 删除项目本地构建文件..."
rm -rf .gradle/
rm -rf app/build/
rm -rf build/

echo "4. 清理全局Gradle缓存..."
rm -rf ~/.gradle/caches/
rm -rf ~/.gradle/daemon/

echo "5. 删除Android Studio缓存..."
rm -rf ~/Library/Caches/AndroidStudio*
rm -rf ~/Library/Application\ Support/AndroidStudio*/.gradle/

echo "6. 杀死所有Java进程（可选）..."
echo "正在查找Java进程..."
pgrep -f java | head -5

read -p "是否要杀死所有Java进程？这会关闭所有Java应用（y/n）: " -n 1 -r
echo
if [[ $REPLY =~ ^[Yy]$ ]]; then
    pkill -f java
    echo "已杀死所有Java进程"
else
    echo "跳过杀死Java进程"
fi

echo "7. 重新下载依赖..."
./gradlew build --refresh-dependencies

echo ""
echo "清理完成！"
echo "===================="
echo "接下来的步骤："
echo "1. 重新启动Android Studio"
echo "2. 重新导入项目"
echo "3. 等待项目同步完成"
echo "===================="