function AddClass(e) {
    return document.querySelector("." + e)
}

function model_add(e, t, o, n, l) {
    e.onmousedown = function () {
        var s = document.createElement("div");
        s.id = t, s.style.cssText = "width: " + n + "px;height: " + l + "px;position: absolute;", s.draggable = !0, document.onmousemove = function (t) {
            t.preventDefault(), document.onmouseup = function (t) {
                var n = t || window.event, l = n.pageX - contentBox.offsetLeft, f = n.pageY - contentBox.offsetTop;
                contentBox.appendChild(s), e.style.color = "red";
                var x = document.createElement("textarea");
                x.value = o, x.style.cssText = "width: 100%;height: 100%;background:rgba(100,100,255,.2);cursor:pointer;", x.readOnly = !0, s.appendChild(x), 0 >= l ? (s.style.left = "0px", s.style.top = "0px") : contentBox.offsetWidth <= l + s.offsetWidth ? (s.style.left = "0px", s.style.top = "0px") : contentBox.offsetHeight <= f + s.offsetHeight ? (s.style.left = "0px", s.style.top = "0px") : 0 >= f ? (s.style.left = "0px", s.style.top = "0px") : (s.style.left = l + "px", s.style.top = f + "px"), model_del(s, e), s.addEventListener("mousedown", function (e) {
                    var t = e || window.event, o = t.offsetX, n = t.offsetY, l = x.offsetWidth, f = x.offsetHeight,
                        d = contentBox.offsetWidth - l, i = contentBox.offsetHeight - f, p = x.offsetWidth,
                        u = x.offsetHeight;
                    x.style.boxShadow = "0px 0px 5px #f00", s.onkeydown = function (e) {
                        var t = e || window.event, o = s.offsetLeft, n = s.offsetTop;
                        37 === t.keyCode && (s.style.left = o <= 2 ? "2px" : o - 2 + "px"), 38 === t.keyCode && (s.style.top = n <= 2 ? "2px" : n - 2 + "px"), 39 === t.keyCode && (s.style.left = o >= d - 2 ? d - 2 + "px" : o + 2 + "px"), 40 === t.keyCode && (s.style.top = n >= i - 2 ? i - 2 + "px" : n + 2 + "px"), x.style.boxShadow = "", document.onmousemove = null, document.onmouseup = null
                    }, document.onmousemove = function (e) {
                        var t = e || window.event;
                        t.preventDefault();
                        var l = x.offsetWidth, f = x.offsetHeight;
                        if (p === l && u === f) {
                            var a = contentBox.offsetLeft, r = contentBox.offsetTop, c = t.pageX - a, y = t.pageY - r,
                                m = c - o, h = y - n;
                            m <= 0 ? h <= 0 ? (s.style.left = "2px", s.style.top = "2px") : h >= i ? (s.style.left = "2px", s.style.top = i - 2 + "px") : (s.style.left = "2px", s.style.top = h + "px") : m >= d ? h <= 0 ? (s.style.left = d - 2 + "px", s.style.top = "2px") : h >= i ? (s.style.left = d - 2 + "px", s.style.top = i - 2 + "px") : (s.style.left = d - 2 + "px", s.style.top = h + "px") : h <= 0 ? (s.style.left = m + "px", s.style.top = "2px") : h >= i ? (s.style.left = m + "px", s.style.top = i - 2 + "px") : (s.style.left = m + "px", s.style.top = h + "px"), document.onmouseup = function () {
                                x.style.boxShadow = "", document.onmousemove = null, document.onmouseup = null
                            }
                        }
                    }
                }), document.onmousemove = null, document.onmouseup = null
            }
        }
    }
}

function model_del(e, t) {
    var o = document.createElement("div");
    o.id = "del_Btn", o.innerHTML = "x", e.appendChild(o), o.onclick = function () {
        contentBox.removeChild(e), t && (t.style.color = "#333")
    }, o.onmousedown = function (e) {
        (e || window.event).stopPropagation()
    }
}

function move(e) {
    var t = e || window.event, o = t.offsetX, n = t.offsetY, l = textarea[this.index].offsetWidth,
        s = textarea[this.index].offsetHeight, f = contentBox.offsetWidth - l, x = contentBox.offsetHeight - s,
        d = textarea[this.index].offsetWidth, i = textarea[this.index].offsetHeight;
    textarea[this.index].style.boxShadow = "0px 0px 5px #f00", this.onkeydown = function (e) {
        var t = e || window.event, o = this.offsetLeft, n = this.offsetTop;
        37 === t.keyCode && (this.style.left = o <= 2 ? "2px" : o - 2 + "px"), 38 === t.keyCode && (this.style.top = n <= 2 ? "2px" : n - 2 + "px"), 39 === t.keyCode && (this.style.left = o >= f - 2 ? f - 2 + "px" : o + 2 + "px"), 40 === t.keyCode && (this.style.top = n >= x - 2 ? x - 2 + "px" : n + 2 + "px"), textarea[this.index].style.boxShadow = "", document.onmousemove = null, document.onmouseup = null
    };
    var p = this;
    document.onmousemove = function (e) {
        var t = e || window.event;
        t.preventDefault();
        var l = textarea[p.index].offsetWidth, s = textarea[p.index].offsetHeight;
        if (d === l && i === s) {
            var u = contentBox.offsetLeft, a = contentBox.offsetTop, r = t.pageX - u, c = t.pageY - a, y = r - o,
                m = c - n;
            y <= 0 ? m <= 0 ? (p.style.left = "2px", p.style.top = "2px") : m >= x ? (p.style.left = "2px", p.style.top = x - 2 + "px") : (p.style.left = "2px", p.style.top = m + "px") : y >= f ? m <= 0 ? (p.style.left = f - 2 + "px", p.style.top = "2px") : m >= x ? (p.style.left = f - 2 + "px", p.style.top = x - 2 + "px") : (p.style.left = f - 2 + "px", p.style.top = m + "px") : m <= 0 ? (p.style.left = y + "px", p.style.top = "2px") : m >= x ? (p.style.left = y + "px", p.style.top = x - 2 + "px") : (p.style.left = y + "px", p.style.top = m + "px"), document.onmouseup = function () {
                textarea[p.index].style.boxShadow = "", document.onmousemove = null, document.onmouseup = null
            }
        }
    }
}

function font_style(e, t) {
}

function font_size() {
}

function text_align() {
}

var broad = document.querySelector(".broad"), tall = document.querySelector(".tall"),
    contentBox = document.querySelector(".content-box"), button = document.querySelector(".button"),
    leftBox_li = document.querySelectorAll(".left-box>li"), contentBoxImg = document.querySelector(".content-box>img");
contentBoxImg && (contentBox.style.width = contentBoxImg.offsetWidth + "px");
var arr = [];
if (document.querySelectorAll(".content-box>div[draggable='true']")) for (var module = document.querySelectorAll(".content-box>div[draggable='true']"), textarea = document.querySelectorAll(".content-box>div[draggable='true']>textarea"), i = module.length - 1; i >= 0; i--) {
    module[i].index = i;
    for (var j = leftBox_li.length - 1; j >= 0; j--) leftBox_li[j].className === module[i].id && (leftBox_li[j].style.color = "#f00", model_del(module[i], leftBox_li[j]), arr.unshift(leftBox_li[j].innerHTML));
    for (var d = textarea.length - 1; d >= 0; d--) textarea[d].value = arr[d];
    module[i].addEventListener("mousedown", move)
}
for (var i = leftBox_li.length - 1; i >= 0; i--) model_add(AddClass(leftBox_li[i].className), leftBox_li[i].className, leftBox_li[i].innerHTML, 100, 30);