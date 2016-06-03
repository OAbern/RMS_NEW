package com.cqupt.mis.rms.controller;

import com.cqupt.mis.rms.dao.ProofDao;
import com.cqupt.mis.rms.model.Proof;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 文件控制器
 * @Author Bern
 */
@Controller
@RequestMapping("/file")
public class FileController {
    @Resource
    ProofDao proofDao;

    /**
     * 旁证材料文件下载
     * @param proofIdStr 旁证材料的id
     * @param response HttpServletResponse
     * @throws NumberFormatException
     * @throws IOException
     */
    @RequestMapping("/proof/download/{pId}")
    public void download(@PathVariable("pId")String proofIdStr, HttpServletResponse response) throws NumberFormatException, IOException {
        int proofId = Integer.parseInt(proofIdStr);
        Proof proof = proofDao.selectByPrimaryKey(proofId);
        File file = new File(proof.getProofPath());

        if(!file.exists()) {        //文件不存在
            try {
                response.getWriter().write("<h2>对不起，您要下载的文件不存在！</h2>");
            } finally {
                response.getWriter().close();
            }
            return;
        }

        FileInputStream fis = new FileInputStream(file);

        try {
            byte[] buffer = new byte[4096];
            int count;
            //设置文件MIME类型
            response.setContentType(proof.getUploadContentType());
            //设置Content-Disposition
            response.setHeader("Content-Disposition", "attachment;filename="+proof.getUploadRealName());
            while((count=fis.read(buffer)) != 0) {
                response.getOutputStream().write(buffer, 0, count);
            }
        } finally {
            response.getOutputStream().close();
        }

    }

}
