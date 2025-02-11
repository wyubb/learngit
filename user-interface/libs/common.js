$(document).ready(function () {
  // 绘制时钟
  drawClock()
  // 绘制日历
  $("#datepicker").datepicker({
    prevText: '<',
    nextText: '>'
  })
  // 处理左侧菜单栏交互
  $('.menu-title').each(function (index) {
    $(this).on('click', function () {
      if ($(this).hasClass('menu-title-active')) {
        $(this).removeClass('menu-title-active')
        $('.menu-title-arrow').eq(index).attr('src', './images/below-arrow.png').attr('title', '展开')
        $('.menu-item-bar').eq(index).removeClass('menu-item-bar-active')
      } else {
        $('.menu-title').each(function () {
          $(this).removeClass('menu-title-active')
        })
        $('.menu-title-arrow').each(function () {
          $(this).attr('src', './images/below-arrow.png').attr('title', '展开')
        })
        $('.menu-item-bar').each(function () {
          $(this).removeClass('menu-item-bar-active')
        })
        $(this).addClass('menu-title-active')
        $('.menu-title-arrow').eq(index).attr('src', './images/up-arrow.png').attr('title', '收起')
        $('.menu-item-bar').eq(index).addClass('menu-item-bar-active')
      }
    })
  })
  $('.menu-item').each(function (index) {
    $(this).on('click', function () {
      $('.menu-item').each(function () {
        $(this).removeClass('menu-item-active')
      })
      $(this).addClass('menu-item-active')
    })
  })
})

// 绘制时钟
function drawClock() {
  var dom=document.getElementById('clock');
  var ctx=dom.getContext('2d');
  var width=ctx.canvas.width;
  var height = ctx.canvas.height;
  var r=width/2;

  //画矩形
  function drawBackground() {
    ctx.save();
    ctx.translate(r, r);
    ctx.beginPath();
    ctx.lineWidth=10;
    ctx.arc(0,0,r-5, 0,2*Math.PI,false);
    ctx.stroke();
    //画数字
    var hourNumbesr=[3,4,5,6,7,8,9,10,11,12,1,2];
    ctx.font='18px Arial';
    ctx.textAlign='center';
    ctx.textBaseline='middle';
    hourNumbesr.forEach(function (number,i){
        var rad=2*Math.PI/12*i;
        var x=Math.cos(rad)*(r-30);
        var y=Math.sin(rad)*(r-30);
        ctx.fillText(number, x, y);
    })
    //画时钟上的小圆点
    for (var i=0; i<60; i++){
      var rad = 2 * Math.PI / 60 * i;
      var x = Math.cos(rad)*(r-18);
      var y = Math.sin(rad)*(r-18);
      ctx.beginPath();
      if (i % 5 === 0){
        ctx.fillStyle="#000";
        ctx.arc(x,y, 2,0,2*Math.PI, false);
      }
      else{
        ctx.fillStyle='#ccc';
        ctx.arc(x,y, 2,0,2*Math.PI, false);
      }
      ctx.fill();
    }
  }
  //画时针
  function drawHour(hour,minute){
    ctx.save();
    ctx.beginPath();
    var rad = 2 * Math.PI /12 * hour;
    var mrad = 2 * Math.PI /12 /60 * minute;
    ctx.rotate(rad+mrad);
    ctx.lineWidth=6;
    ctx.lineCap='round';
    ctx.moveTo(0,10);
    ctx.lineTo(0,-r/2);
    ctx.stroke();
    ctx.restore();
  }
  //画分针
  function  drawMinute(minute,second) {
    ctx.save();
    ctx.beginPath();
    var rad = 2 * Math.PI /60 * minute;
    var mrad = 2 * Math.PI /60 /60 * second;
    ctx.rotate(rad+mrad);
    ctx.lineWidth=3;
    ctx.lineCap='round';
    ctx.moveTo(0,10);
    ctx.lineTo(0,-r+30);
    ctx.stroke();
    ctx.restore();
  }
  //画秒针
  function  drawSecond(second) {
    ctx.save();
    ctx.beginPath();
    ctx.fillStyle='#c14543';
    var rad = 2 * Math.PI /60 * second;
    ctx.rotate(rad);
    ctx.moveTo(-2,20);
    ctx.lineTo(2,20);
    ctx.lineTo(1,-r+18);
    ctx.lineTo(-1,-r+18);
    ctx.fill();
    ctx.restore();
  }
  //画中心定点
  function  drawDot() {
    ctx.beginPath();
    ctx.fillStyle='#fff';
    ctx.arc(0,0,3,0,2 * Math.PI, false);
    ctx.fill();
  }
  //获取当前时间并执行函数
  function draw() {
    ctx.clearRect(0,0,width, height);
    drawBackground();
    drawDot();
    var now = new Date();
    var hour = now.getHours();
    var minute = now.getMinutes();
    var second = now.getSeconds();
    drawMinute(minute,second);
    drawHour(hour,minute);
    drawSecond(second);
    ctx.restore();
  }
  draw();
  setInterval(draw,1000);
}