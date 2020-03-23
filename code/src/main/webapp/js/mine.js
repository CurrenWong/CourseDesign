// 激活对应边栏
$(function () {
    $('.nav-item').find('a').each(function () {
        // 若页面跳转链接与本页面链接一致，则激活该边栏
        if (this.href == document.location.href || document.location.href.search(this
            .href) >= 0) {
            // 隐藏其他边栏选项
            $(this).parent().parent().parent().siblings('li').removeClass('active');
            // 激活父选项
            $(this).parent().parent().parent().addClass('active');
            // 打开选项卡
            $(this).parent().parent().addClass('show');
            // 激活子选项
            $(this).addClass('active');
        }
    });
});

