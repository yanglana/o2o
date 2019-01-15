$(function () {
    // 获取此店铺下的商品列表的URL
    var listUrl = "/o2omaven/shopadmin/getproductlistbyshop?pageIndex=1&pageSize=999";
    // 商品下架URL
    var statusUrl = "/o2omaven/shopadmin/modifyproduct";

    getList();

    /*
     * @Description 获取此店铺下的商品列表
     */
    function getList() {
        // 从后台获取此店铺的商品列表
        $.getJSON(
            listUrl, function (data) {
                if (data.success) {
                    var productList = data.productList;
                    var tempHtml = '';
                    // 遍历每条商品信息，拼接成一行显示，列信息包括：
                    // 商品名称，优先级，上架\下架(含productId)，编辑按钮(含productId)
                    // 预览(含productId)
                    productList.map(function (index, domElement) {
                        var textOp = "下架";
                        var contraryStatus = 0;
                        if (index.enableStatus == 0) {
                            // 若状态值为0，表明是已下架的商品，操作变为上架(即点击上架按钮上架相关商品)
                            textOp = "上架";
                            contraryStatus = 1;
                        } else {
                            contraryStatus = 0;
                        }
                        // 拼接每件商品的行信息
                        tempHtml += '<div class="row row-product">' +
                                        '<div class="col-33">'+index.productName+'</div>' +
                                        '<div class="col-20">'+index.point+'</div>' +
                                        '<div class="col-40">' +
                                            '<a href="#" class="edit" data-id="'+index.productId+'" data-status="'+index.enableStatus+'">编辑</a>' +
                                            '<a href="#" class="status" data-id="'+index.productId+'" data-status="'+contraryStatus+'">'+textOp+'</a>' +
                                            '<a href="#" class="preview" data-id="'+index.productId+'" data-status="'+index.enableStatus+'">预览</a>' +
                                        '</div>' +
                                    '</div>';
                    });
                    // 将拼接好的信息赋值进html控件中
                    $('.product-wrap').html(tempHtml);
                }
            }
        );
    }

    // 将class为product-wrap里面的a标签绑定上点击的事件
    $('.product-wrap').on('click','a',function (e) {
        var target = $(e.currentTarget);
        if (target.hasClass('edit')) {
            // 如果有class edit则点击就进入店铺信息编辑页面，并带有productId参数
            window.location.href = '/o2omaven/shopadmin/productoperation?productId='+e.currentTarget.dataset.id;
        } else if (target.hasClass('status')) {
            // 如果有class status则调用后台功能上/下架相关商品，并带有productId参数
            changeItemStatus(e.currentTarget.dataset.id,e.currentTarget.dataset.status);
        } else if (target.hasClass('preview')) {
            // 如果有class preview则去前台展示系统该商品详情页预览商品情况
            window.location.href = '/o2omaven/frontend/productdetail?productId='+e.currentTarget.dataset.id;
        }
    });

    $('#new').on('click',function () {
       window.location.href = "/o2omaven/shopadmin/productoperation";
    });

    $('#back').on('click',function () {
        window.location.href = "/o2omaven/shopadmin/shopmanagement";
    });

    function changeItemStatus(id,enableStatus) {
        // 定义product json对象并添加productId以及状态(上架/下架)
        var product = {};
        product.productId = id;
        product.enableStatus = enableStatus;
        $.confirm('确定么?',function () {
            // 上下架相关商品
            $.ajax({
                url:statusUrl,
                type:'POST',
                data:{
                    productStr:JSON.stringify(product),
                    statusChange:true
                },
                dataType:'json',
                success:function (data) {
                    if (data.success) {
                        $.toast('操作成功！');
                        getList();
                    } else {
                        $.toast('操作失败！');
                    }
                }
            });
        });
    }
});