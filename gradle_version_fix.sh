#!/bin/bash

# Gradle版本兼容性修复脚本
# 检查并修复Gradle和Android Gradle Plugin版本兼容性

echo "检查并修复Gradle版本兼容性..."

PROJECT_PATH="/Users/lilili/Documents/工作/other/shiwu"
cd "$PROJECT_PATH"

echo "1. 检查当前Gradle版本..."
echo "Gradle Wrapper版本："
cat gradle/wrapper/gradle-wrapper.properties | grep distributionUrl

echo ""
echo "2. 检查Android Gradle Plugin版本..."
echo "AGP版本："
grep "com.android.tools.build:gradle" build.gradle

echo ""
echo "3. 推荐的版本组合："
echo "===================="
echo "对于较新的项目："
echo "- Gradle 8.0+"
echo "- Android Gradle Plugin 8.0+"
echo "- compileSdkVersion 33+"
echo ""
echo "对于较老的项目："
echo "- Gradle 7.4"
echo "- Android Gradle Plugin 7.4.2"
echo "- compileSdkVersion 32"

echo ""
read -p "是否要更新到推荐的稳定版本组合？(y/n): " -n 1 -r
echo

if [[ $REPLY =~ ^[Yy]$ ]]; then
    echo "4. 创建备份..."
    cp gradle/wrapper/gradle-wrapper.properties gradle/wrapper/gradle-wrapper.properties.backup
    cp build.gradle build.gradle.backup
    
    echo "5. 更新Gradle Wrapper到7.4..."
    sed -i '' 's|distributionUrl=.*|distributionUrl=https\\://services.gradle.org/distributions/gradle-7.4-bin.zip|' gradle/wrapper/gradle-wrapper.properties
    
    echo "6. 更新Android Gradle Plugin到7.4.2..."
    sed -i '' 's/com.android.tools.build:gradle:[^'\''"]*/com.android.tools.build:gradle:7.4.2/' build.gradle
    
    echo "7. 更新完成！"
    echo "新的Gradle Wrapper版本："
    cat gradle/wrapper/gradle-wrapper.properties | grep distributionUrl
    echo "新的AGP版本："
    grep "com.android.tools.build:gradle" build.gradle
else
    echo "跳过版本更新"
fi

echo ""
echo "修复完成！请重新同步项目"