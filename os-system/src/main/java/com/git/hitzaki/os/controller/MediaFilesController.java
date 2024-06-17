package com.git.hitzaki.os.controller;

import com.git.hitzaki.base.exception.BizCenterException;
import com.git.hitzaki.base.model.PageParams;
import com.git.hitzaki.base.model.PageResult;
import com.git.hitzaki.base.model.RestResponse;
import com.git.hitzaki.os.model.dto.QueryMediaParamsDto;
import com.git.hitzaki.os.model.dto.UploadFileParamsDto;
import com.git.hitzaki.os.model.dto.UploadFileResultDto;
import com.git.hitzaki.os.model.po.MediaFiles;
import com.git.hitzaki.os.service.MediaFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author hitzaki
 * @version 1.0
 * @description 媒资文件管理接口
 */
@Api(value = "媒资文件管理接口", tags = "媒资文件管理接口")
@RestController
public class MediaFilesController {


    @Autowired
    MediaFileService mediaFileService;


    @ApiOperation("媒资列表查询接口")
    @PostMapping("/files")
    public PageResult<MediaFiles> list(PageParams pageParams, @RequestBody(required = false) QueryMediaParamsDto queryMediaParamsDto) {
        return mediaFileService.queryMediaFiels(pageParams, queryMediaParamsDto);

    }

    @RequestMapping(value = "/upload/file", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public UploadFileResultDto upload(@RequestPart("file") MultipartFile file,
                                      @RequestParam(value = "folder",required=false) String folder,
                                      @RequestParam(value= "objectName",required=false) String objectName) {


        UploadFileParamsDto uploadFileParamsDto = new UploadFileParamsDto();
        String contentType = file.getContentType();
        uploadFileParamsDto.setContentType(contentType);
        uploadFileParamsDto.setFileSize(file.getSize());//文件大小
        if (contentType.indexOf("image") >= 0) {
            //是个图片
            uploadFileParamsDto.setFileType("001001");
        } else {
            uploadFileParamsDto.setFileType("001003");
        }
        uploadFileParamsDto.setFilename(file.getOriginalFilename());//文件名称
        UploadFileResultDto uploadFileResultDto = null;
        try {
            uploadFileResultDto = mediaFileService.uploadFile(uploadFileParamsDto, file.getBytes(), folder, objectName);
        } catch (Exception e) {
            BizCenterException.cast("上传文件过程中出错");
        }

        return uploadFileResultDto;

    }

    @ApiOperation("预览文件")
    @GetMapping("/preview/{mediaId}")
    public RestResponse<String> getPlayUrlByMediaId(@PathVariable String mediaId){

        //调用service查询文件的url

        MediaFiles mediaFiles = mediaFileService.getFileById(mediaId);
        return RestResponse.success(mediaFiles.getUrl());
    }

}
