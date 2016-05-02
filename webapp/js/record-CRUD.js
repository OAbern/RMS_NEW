/**
 * Created by Bern on 2016/5/2.
 */
var personCount = 0;        //成员计数器，全局作用域
/**
 * 增加相关人员
 */
$('#addPerson').click(function() {
    //$('#a-person').click();
    personCount++;
    $('#person').append('<div class="form-group"><hr class="hr-double"><button type="button" class="btn btn-warning btn-xs" onclick="deletePerson(this)">删除</button><div class="clear-left"></div><div class="line-25-per"><label>相关成员姓名</label><input class="form-control" name="pName'+personCount+'"><p class="help-block text-info">可选填</p> </div><div class="line-25-per"><label>备注</label><input class="form-control" name="pRemark'+personCount+'"> <p class="help-block text-info">可选填</p></div><div class="line-25-per"><label>排名</label><input class="form-control" name="pOrder'+personCount+'"> <p class="help-block text-info">可选填</p></div><div class="clear-left"></div></div><!-- /.form-group -->');
    window.parent.iFrameHeight();   //iframe自适应高度
});

/**
 * 增加材料
 */
$('#addProof').click(function() {
    //$('#a-proof').click();
    $('#proof').append('<div class="form-group"><hr class="hr-double"><button type="button" class="btn btn-warning btn-xs" onclick="deleteProof(this)">删除</button><div class="clear-left"></div><div class="line-25-per"><label>旁证材料上传</label><input type="file" name="proof"> </div><div class="clear-left"></div></div><!-- /.form-group -->');
    window.parent.iFrameHeight();   //iframe自适应高度
});

/**
 * 删除相关人员
 * @param ref
 */
function deletePerson(ref) {
    personCount--;

    ref.closest('.form-group').remove();
    window.parent.iFrameHeight();   //iframe自适应高度
}

/**
 * 删除材料
 * @param ref
 */
function deleteProof(ref) {
    ref.closest('.form-group').remove();
    window.parent.iFrameHeight();   //iframe自适应高度
}

/**
 * 设置提交状态值
 * @param status
 */
function setStatus(status) {
    $('#status').val(status);
}