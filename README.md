# FireworkShop - 烟花商店​

## 介绍
 本款插件是根据插件交流群中的【魔力】首次提出的想法编写的，其中为本插件出谋划策的还有【果先生】，本插件为各位服主提供烟花商店的功能，可自定义模板，让服主也能自定义烟花的燃放效果，从而实现美丽的烟花秀。
 特别鸣谢:魔力：本插件想法的提出者、果先生：为功能完善提供参考建议

## 指令
/fs 打开菜单
/fsaccept 接受烟花邀请

## 配置教程​
本插件的配置文件需要规范格式，默认会为各位服主生成一个默认模板，可按照其进行修改！目前可支持的烟花类型和颜色如下:
颜色: RED、BLACK、BLUE、BROWN、CYAN、GREEN、LIGHT_BLUE、LIGHT_GRAY、LIME、MAGENTA、ORANGE、PINK、PURPLE、WHITE、YELLOW
烟花爆炸类型: BURST、LARGE_BALL、SMALL_BALL、STAR_SHAPED、CREEPER_SHAPED

```
shops.yml：
---
test:
  类型: 0 #类型0为普通类型
  description: none #显示介绍
  是否原地发射: false #false则为固定坐标，否则为原地发射
  位置:
   - 128.000000
   - 4.000000
   - 129.000000
  所在世界: world
  烟花类型:
  - STAR_SHAPED
  - RED
  货币花费:
    - "金币:100"
    - "点券:0"
  连发次数: 10
test2: #更新1.0.2请修改此项
  类型: 1
  description: none
  模板: test
  是否原地发射: false
  位置:
    - 118.000000
    - 4.000000
    - 111.000000
  所在世界: world
  货币花费:
    - "金币:100"
```
```
---
MaxTicks: 50 #本插件中1tick算作0.1秒！！！注意！！！
'1': #数字必须用 ' ' 包含在内，以免出现报错！
  - STAR_SHAPED:RED:0.000000:0.000000:0.000000
# 烟花爆炸类型:烟花颜色:x轴偏移:y轴偏移:z轴偏移
  - STAR_SHAPED:RED:1.000000:0.000000:0.000000
  - STAR_SHAPED:RED:-1.000000:0.000000:0.000000
'5':
  - STAR_SHAPED:RED:1.000000:0.000000:0.000000
'10':
  - STAR_SHAPED:RED:2.000000:0.000000:0.000000
'15':
  - STAR_SHAPED:RED:-1.000000:0.000000:0.000000
'20':
  - STAR_SHAPED:RED:-2.000000:0.000000:0.000000
'25':
  - STAR_SHAPED:RED:0.000000:0.000000:1.000000
'30':
  - STAR_SHAPED:RED:0.000000:0.000000:2.000000
'35':
  - STAR_SHAPED:RED:0.000000:0.000000:-1.000000
'40':
  - STAR_SHAPED:RED:0.000000:0.000000:-2.000000
```

## Template Example
```
template_01.yml：
---
MaxTicks: 50
'10':
  - LARGE_BALL:YELLOW:-1.000000:0.000000:1.000000
  - STAR_SHAPED:GREEN:1.000000:0.000000:1.000000
  - STAR_SHAPED:RED:-1.000000:0.000000:-1.000000
  - STAR_SHAPED:ORANGE:-1.000000:0.000000:1.000000
  - STAR_SHAPED:PINK:0.000000:0.000000:1.000000
  - STAR_SHAPED:YELLOW:0.000000:0.000000:-1.000000
  - STAR_SHAPED:GREEN:1.000000:0.000000:0.000000
  - STAR_SHAPED:ORANGE:-1.000000:0.000000:0.000000
'20':
  - LARGE_BALL:RED:-2.000000:0.000000:2.000000
  - STAR_SHAPED:GREEN:2.000000:0.000000:2.000000
  - STAR_SHAPED:PINK:-2.000000:0.000000:-2.000000
  - STAR_SHAPED:RED:-2.000000:0.000000:2.000000
  - STAR_SHAPED:ORANGE:0.000000:0.000000:2.000000
  - STAR_SHAPED:RED:0.000000:0.000000:-2.000000
  - STAR_SHAPED:PINK:2.000000:0.000000:0.000000
  - STAR_SHAPED:ORANGE:-2.000000:0.000000:0.000000
'30':
  - LARGE_BALL:RED:-3.000000:0.000000:3.000000
  - STAR_SHAPED:PINK:3.000000:0.000000:3.000000
  - STAR_SHAPED:PINK:-3.000000:0.000000:-3.000000
  - STAR_SHAPED:ORANGE:-3.000000:0.000000:3.000000
  - STAR_SHAPED:RED:0.000000:0.000000:3.000000
  - STAR_SHAPED:GREEN:0.000000:0.000000:-3.000000
  - STAR_SHAPED:ORANGE:3.000000:0.000000:0.000000
  - STAR_SHAPED:RED:-3.000000:0.000000:0.000000
'35':
  - LARGE_BALL:RED:-1.000000:0.000000:1.000000
  - STAR_SHAPED:GREEN:1.000000:0.000000:1.000000
  - STAR_SHAPED:RED:-1.000000:0.000000:-1.000000
  - STAR_SHAPED:ORANGE:-1.000000:0.000000:1.000000
  - STAR_SHAPED:PINK:0.000000:0.000000:1.000000
  - STAR_SHAPED:GREEN:0.000000:0.000000:-1.000000
  - STAR_SHAPED:ORANGE:1.000000:0.000000:0.000000
  - STAR_SHAPED:PINK:-1.000000:0.000000:0.000000
'40':
  - LARGE_BALL:ORANGE:-2.000000:0.000000:2.000000
  - STAR_SHAPED:ORANGE:2.000000:0.000000:2.000000
  - STAR_SHAPED:GREEN:-2.000000:0.000000:-2.000000
  - STAR_SHAPED:GREEN:-2.000000:0.000000:2.000000
  - STAR_SHAPED:PINK:0.000000:0.000000:2.000000
  - STAR_SHAPED:ORANGE:0.000000:0.000000:-2.000000
  - STAR_SHAPED:PINK:2.000000:0.000000:0.000000
  - STAR_SHAPED:RED:-2.000000:0.000000:0.000000
'50':
  - LARGE_BALL:RED:-3.000000:0.000000:3.000000
  - STAR_SHAPED:RED:3.000000:0.000000:3.000000
  - STAR_SHAPED:ORANGE:-3.000000:0.000000:-3.000000
  - STAR_SHAPED:RED:-3.000000:0.000000:3.00000
  - STAR_SHAPED:GREEN:0.000000:0.000000:3.000000
  - STAR_SHAPED:ORANGE:0.000000:0.000000:-3.000000
  - STAR_SHAPED:ORANGE:3.000000:0.000000:0.000000
  - STAR_SHAPED:ORANGE:-3.000000:0.000000:0.000000
```

## 截图&视频​
[![Wn85VK.md.png](https://z3.ax1x.com/2021/07/15/Wn85VK.md.png)](https://imgtu.com/i/Wn85VK)
Click here to see more: https://www.minebbs.com/resources/fireworkshop.2485/?btwaf=71634949
