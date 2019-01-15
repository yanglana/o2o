$(function () {
    var listUrl = "/o2omaven/shopadmin/getproductcategorylist";
    var addUrl = "/o2omaven/shopadmin/addproductcategorys";
    var deleteUrl = "/o2omaven/shopadmin/removeproductcategory";

    getList();

    function getList() {
        $.getJSON(
            listUrl, function (data) {
                if (data.success) {
                    var dataList = data.data;
                    //$('.category-wrap').html('');
                    var tempHtml = '';
                    dataList.map(function (index, domElement) {
                        tempHtml += '<div class="row row-product-category now">' +
                            '<div class="col-33">'
                            + index.productCategoryName +
                            '</div>' +
                            '<div class="col-33">'
                            + index.priority +
                            '</div>' +
                            '<div class="col-33">' +
                            '<a href="#" class="button delete" data-id="' + index.productCategoryId + '">删除</a>' +
                            '</div>' +
                            '</div>';
                    });
                    $('.category-wrap').html(tempHtml);
                }
            }
        );
    }

    $('#new').click(function () {
        //给每次新加的做上temp的标记
        var tempHtml = '<div class="row row-product-category temp">' +
            '<div class="col-33"><input class="category-input category" type="text" placeholder="分类名"></div>' +
            '<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"></div>' +
            '<div class="col-33"><a href="#" class="button delete">删除</a></div>' +
            '</div>';
        $('.category-wrap').append(tempHtml);
    });

    $('#submit').click(function () {
        var tempArr = $('.temp');
        var productCategoryList = [];
        //遍历获取新加中的值(在创建的时候要标记)
        tempArr.map(function (index, domElement) {
            var tempObj = {};
            tempObj.productCategoryName = $(domElement).find('.category').val();
            tempObj.priority = $(domElement).find('.priority').val();
            if (tempObj.productCategoryName && tempObj.priority) {
                //加入数组
                productCategoryList.push(tempObj);
            }
        });
        $.ajax({
            url: addUrl,
            type: 'POST',
            //将数组转换成JSON字符串形式
            data: JSON.stringify(productCategoryList),
            contentType: 'application/json',
            success: function (data) {
                if (data.success) {
                    $.toast('提交成功!');
                    getList();
                } else {
                    $.toast('提交失败!');
                }
            }
        });
    });

    $('.category-wrap').on('click', '.row-product-category.temp .delete',
        function (e) {
            console.log($(this).parent().parent());
            $(this).parent().parent().remove();
        }
    );

    $('.category-wrap').on('click', '.row-product-category.now .delete',
        function (e) {
            var target = e.currentTarget;
            $.confirm('确定么?', function () {
                $.ajax({
                    url: deleteUrl,
                    type: 'POST',
                    data: {
                        productCategoryId: target.dataset.id
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            $.toast('删除成功!');
                            getList();
                        } else {
                            $.toast('删除失败!');
                        }
                    }
                });
            });
        }
    );
});