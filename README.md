# CascadePickerView
## 级联选择组件,包括*基础组件*和*城市选择组件*
> 目前公开的记录组件基本都有限制，限制选择级别，如很多城市组件只能选择三级：省、市、县   
> 本组件不限制级别，可无极显示，只要按照要求组织数据即可   

## 引用方式
```groovy
    implementation 'space.tanghy:cascade:0.0.2'
```

## 基础级联组件 CascadePickerView
> 组件提供级别可根据需求设置显示级别

```kotlin
 setLevel()
```
> 组件默认到最后一级关闭级联选择框，可通过设置属性不关闭

```kotlin
lastItemCloseDialog = false
```
## 数据格式
````json
[

  {
    "value": "11",
    "name": "北京市",
    "children": [{
      "value": "1101",
      "name": "朝阳",
      "children": [

      ]
    }]
  }
]
````
> children属性可无线添加下去
## setData
> 组织好数据可通过setData方法设置数据
````kotlin
val dialog = CascadePickerView(
    this,
    mutableListOf(
        Item(
            "北京", "北京", mutableListOf(
                Item("朝阳区", "朝阳区"),
                Item("东城区", "东城区"),
                Item("西城区", "西城区"),
            )
        ),
        Item("天津市", "天津市"),
        Item("河北省", "河北省"),
        Item("山西省", "山西省"),
        Item("内蒙古", "内蒙古"),
        Item("辽宁省", "辽宁省"),
        Item("吉林省", "吉林省"),
        Item("黑龙江省", "黑龙江省"),
        Item("上海市", "上海市"),
    )
)
// 或者使用JSON字符串进行设置数据
dialog.setData("[\n" +
        "  {\n" +
        "    \"value\": \"1401\",\n" +
        "    \"name\": \"太原市\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"value\": \"1402\",\n" +
        "    \"name\": \"大同市\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"value\": \"1403\",\n" +
        "    \"name\": \"阳泉市\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"value\": \"1404\",\n" +
        "    \"name\": \"长治市\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"value\": \"1405\",\n" +
        "    \"name\": \"晋城市\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"value\": \"1406\",\n" +
        "    \"name\": \"朔州市\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"value\": \"1407\",\n" +
        "    \"name\": \"晋中市\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"value\": \"1408\",\n" +
        "    \"name\": \"运城市\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"value\": \"1409\",\n" +
        "    \"name\": \"忻州市\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"value\": \"1410\",\n" +
        "    \"name\": \"临汾市\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"value\": \"1411\",\n" +
        "    \"name\": \"吕梁市\"\n" +
        "  }\n" +
        "]")
````

## searchChildren 动态指定数据源
> 该方法返回的数据需要跟框架指定的格式一致，数据将动态赋给下一级节点

## 城市选择器组件 CityPickerView
> 行政区划数据是国家统计局发布的区划数据2021版，区划数据需要解析下载后续将更新区划升级api   
> 国家统计局区划地址：http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2021/index.html   
> 行政区划数据量：61万多

### 城市级别
- 省级
- 市级
- 县级
- 乡镇级
- 村级
### 实例
```kotlin
    val dialog = CityPickerView(this)
    dialog.lastItemCloseDialog = false
    dialog.show()
```
<img src="http://82.157.109.86/images/1.png" alt="图片2" width="313" height="656"> <img src="http://82.157.109.86/images/2.png" alt="图片2" width="313" height="656">

## 0.0.3版本
- 更新行政区划数据到村（后续可能考虑删除，村一级数据过大导致打包、上传或者使用都不方便）   
- 更新级联选择组件可传入json字符串为数据      
- 区划数据已经拆分，有需要原数据的可以留言。