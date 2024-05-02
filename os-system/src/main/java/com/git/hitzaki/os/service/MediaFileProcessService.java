package com.git.hitzaki.os.service;

import com.git.hitzaki.os.model.po.MediaProcess;

import java.util.List;

/**
 * @author hitzaki
 * @version 1.0
 */
public interface MediaFileProcessService {


    /**
     * @param shardIndex 分片序号
     * @param shardTotal 分片总数
     * @param count      获取记录数
     * @return java.util.List<com.git.hitzaki.os.model.po.MediaProcess>
     * @description 获取待处理任务
     * @author hitzaki
     */
    public List<MediaProcess> getMediaProcessList(int shardIndex, int shardTotal, int count);


    /**
     * @param taskId   任务id
     * @param status   任务状态
     * @param fileId   文件id
     * @param url      url
     * @param errorMsg 错误信息
     * @return void
     * @description 保存任务结果
     * @author hitzaki
     */
    void saveProcessFinishStatus(Long taskId, String status, String fileId, String url, String errorMsg);
}
