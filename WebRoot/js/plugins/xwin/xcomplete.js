
XComplete = function () { this.init.apply(this, arguments) };
XComplete.prototype = {
    color: '#000',
    overcolor: '#000',
    background: '#fff',
    bordercolor: '#79b',
    overground: '#f6f6f6',
    fontFamily: 'arial',
    fontSize: '12px',
    offset: { x: 0, y: 0, w: 0 },
    ie6: /MSIE 6/.test(navigator.userAgent),
    init: function (p) {
        var _ = this, o;
        for (var k in p) _[k] = p[k];
        if (_.ie6) {
            o = document.createElement('IFRAME');
            o.style.position = 'absolute';
            o.setAttribute('frameborder', 'no');
            document.body.appendChild(o);
            _.iframe = o;
        }
        if (_.id) _.input = document.getElementById(_.id)
        o = _.input;
        o.setAttribute('autocomplete', 'off');
        p = document.createElement('DIV');
        p.style.position = 'absolute';
        p.style.zIndex = '100000000';
        p.style.overflow = 'hidden';
        p.style.textAlign = 'left';
        p.style.cursor = 'default';
        if (_.className) {
            p.style.className = _.className;
        } else {
            p.style.color = _.color;
            p.style.background = _.background;
            p.style.fontFamily = _.fontFamily;
            p.style.fontSize = _.fontSize;
            p.style.border = 'solid 1px ' + _.bordercolor;
        }
        p.onclick = function (e) { _.mouseover(e, 1); _.hide() };
        p.onmousedown = function (e) { _.ismousedown = true; };
        p.onmouseup = function (e) { _.ismousedown = false };
        p.onmouseover = function (e) { _.mouseover(e) };
        p.oncontextmenu = function () { _.ishide = 1; _.hide(); return false };
        document.body.appendChild(p);
        _.div = p;
        _.resize();
        _.hide();
        _.E(o, 'blur', function (e) { var e = e || event; if (_.ismousedown) return; setTimeout(function () { _.hide() }, 100) });
        _.E(o, 'keyup', function (e) { _.keyup(e) });
        _.E(o, 'keydown', function (e) { _.keydown(e) });
        _.E(window, 'resize', function () { _.resize() });
    },
    E: function (o, e, f) { if (window.attachEvent) { o.attachEvent('on' + e, f) } else { o.addEventListener(e, f, false) } },
    fo: function (o) { var x = y = 0; while (o) { x += o.offsetLeft; y += o.offsetTop; o = o.offsetParent } return { x: x, y: y} },
    onchange: function (o) { if (o) this.input.value = o.innerHTML },
    hide: function () { this.div.style.display = 'none'; if (this.iframe) this.iframe.style.display = 'none'; },
    show: function (s) {
        var _ = this, c, p = _.div;
        p.innerHTML = s;
        p.style.display = '';
        for (var i = 0; i < p.childNodes.length; i++) {
            c = p.childNodes[i]
            c.n = i + 1;
            c.style.margin = '0';
            c.style.padding = '2px 3px';
        }
        _.resize();
        _.updateIframe();
    },
    updateIframe: function () {
        var _ = this, p = _.div, q = _.iframe;
        if (!q) return;
        q.style.display = '';
        q.style.left = p.style.left;
        q.style.top = p.style.top;
        q.style.width = p.offsetWidth + 'px';
        q.style.height = p.offsetHeight + 'px';
    },
    resize: function () {
        var _ = this, o = _.input, p = _.div, u = _.fo(o), v = _.offset, w = o.offsetWidth + v.w - 2;
        if (w < 0) return;
        p.style.left = u.x + v.x + 'px';
        p.style.top = u.y + o.offsetHeight - 1 + v.y + 'px';
        p.style.width = w + 'px';
        _.updateIframe();
    },
    keyup: function (e) {
        var _ = this, c, s, o = _.input, k = (e || event).keyCode;
        if (_.ishide) return;
        if ((k >= 37 && k <= 40) || k == 13) return;
        s = _.value = o.value;
        _.n = 0;
        _.onchange();
        if (!s) { _.hide(); return };
        s = s.replace(/&/g, '%26');
        _.callback(s);
    },
    keydown: function (e) {
        var _ = this, p = _.div, m, n = _.n, l = p.childNodes.length;
        if (p.style.display == 'none' || !l) return;
        if (!n || n > l) n = 0;
        switch ((e || event).keyCode) {
            case 38: n--; if (n == -1) n = l; break;
            case 40: n++; if (n == l + 1) n = 0; break;
            case 13: if (_.onclick) _.onclick(); _.hide(); return;
            case 9: case 27: _.hide(); return;
            default: return;
        }
        _.active(n);
    },
    mouseover: function (e, b) {
        var _ = this, p = _.div, o = (e = e || event).target || e.srcElement;
        if (!o || o == p) return;
        while (o.parentNode != p) { o = o.parentNode }
        if (b) { _.onchange(o); if (_.onclick) _.onclick() } else { _.active(o.n, 1) }
    },
    active: function (n, b) {
        var _ = this, o, p = _.div.childNodes;
        if (_.n) { o = p[_.n - 1]; o.style.background = _.bground; o.style.color = _.bcolor; }
        if (n) {
            o = p[n - 1];
            _.bcolor = o.style.color;
            _.bground = o.style.background;
            o.style.background = _.overground;
            o.style.color = _.overcolor;
        }
        if (!b) {
            if (!n) _.input.value = _.value;
            _.onchange(n ? o : '');
        }
        _.n = n;
    }
}