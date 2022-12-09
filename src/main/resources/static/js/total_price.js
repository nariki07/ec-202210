"use strict";
$(function () {
  calc_price();
  $(".size").on("change", function () {
    calc_price();
  });

  $(".checkbox").on("change", function () {
    calc_price();
  });

  $("#toynum").on("change", function () {
    calc_price();
  });

  // 値段の計算をして変更する関数
  function calc_price() {
    let size = $(".size:checked").val();
    let topping_count = $("#topping input:checkbox:checked").length;
    let toy_num = $("#toynum").val();
    let size_price = 0;
    let topping_price = 0;
    if (size === "m") {
      size_price = $("#priceM").val();
      topping_price = 200 * topping_count;
    } else if (size === "l") {
      size_price = $("#priceL").val();
      topping_price = 300 * topping_count;
    }
    let numberSizePrice = Number(size_price);
    let price = (numberSizePrice + topping_price) * toy_num;
    $("#totalprice").text(price.toLocaleString());
  }
});
