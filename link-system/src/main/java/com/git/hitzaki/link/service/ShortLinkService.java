/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.git.hitzaki.link.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.git.hitzaki.base.model.PageParams;
import com.git.hitzaki.base.model.PageResult;
import com.git.hitzaki.link.dao.entity.ShortLinkDO;
import com.git.hitzaki.link.dto.req.ShortLinkBatchCreateReqDTO;
import com.git.hitzaki.link.dto.req.ShortLinkCreateReqDTO;
import com.git.hitzaki.link.dto.req.ShortLinkPageReqDTO;
import com.git.hitzaki.link.dto.req.ShortLinkUpdateReqDTO;
import com.git.hitzaki.link.dto.resp.ShortLinkBatchCreateRespDTO;
import com.git.hitzaki.link.dto.resp.ShortLinkCreateRespDTO;
import com.git.hitzaki.link.dto.resp.ShortLinkPageRespDTO;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;


public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * 创建短链接
     *
     * @param requestParam 创建短链接请求参数
     * @return 短链接创建信息
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);

    /**
     * 批量创建短链接
     *
     * @param requestParam 批量创建短链接请求参数
     * @return 批量创建短链接返回参数
     */
    ShortLinkBatchCreateRespDTO batchCreateShortLink(ShortLinkBatchCreateReqDTO requestParam);

    /**
     * 修改短链接
     *
     * @param requestParam 修改短链接请求参数
     */
    void updateShortLink(ShortLinkUpdateReqDTO requestParam);

    /**
     * 分页查询短链接
     * @return 短链接分页返回结果
     */
    PageResult<ShortLinkDO> pageShortLink(PageParams pageParams);

    /**
     * 短链接跳转
     *
     * @param shortUri 短链接后缀
     * @param request  HTTP 请求
     * @param response HTTP 响应
     */
    void restoreUrl(String shortUri, ServletRequest request, ServletResponse response);

    void disableLink(String shortUri);
}
