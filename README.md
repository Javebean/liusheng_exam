# step1
> 首先要添加知识点，因为每一个题目都要关联一个知识点。

#Demo
![alt tag](https://github.com/Javebean/rotatepic/blob/master/pic/rorate.gif)

#Usage
relax , it's very easy!

```javascript
 var rotate = new Rotate({
    	canvasHeight:500,
    	canvasWidth:300,
    	canvasBgcolor: '#fff',
    	canvasBorderWidth: '1px',
    	canvasBorderColor: 'red',
    	imageSrc:'pic/hah.png'
    });
    
    $("#clockwise").click(function(){ 
        rotate.clockwise();
    });

    $("#counterclockwise").click(function(){ 
        rotate.counterclockwise();
    });
```

