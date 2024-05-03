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

package com.git.hitzaki.link.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.git.hitzaki.base.model.RestResponse;
import com.git.hitzaki.link.dto.req.ShortLinkBatchCreateReqDTO;
import com.git.hitzaki.link.dto.req.ShortLinkCreateReqDTO;
import com.git.hitzaki.link.dto.req.ShortLinkPageReqDTO;
import com.git.hitzaki.link.dto.req.ShortLinkUpdateReqDTO;
import com.git.hitzaki.link.dto.resp.ShortLinkBatchCreateRespDTO;
import com.git.hitzaki.link.dto.resp.ShortLinkCreateRespDTO;
import com.git.hitzaki.link.dto.resp.ShortLinkPageRespDTO;
import com.git.hitzaki.link.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    /**
     * 短链接跳转原始链接
     */
    @GetMapping("/{short-uri}")
    public void restoreUrl(@PathVariable("short-uri") String shortUri, ServletRequest request, ServletResponse response) {
        shortLinkService.restoreUrl(shortUri, request, response);
    }

    /**
     * 创建短链接
     */
    @PostMapping("/api/short-link/v1/create")
    public RestResponse<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return RestResponse.success(shortLinkService.createShortLink(requestParam));
    }


    /**
     * 批量创建短链接
     */
    @PostMapping("/api/short-link/v1/create/batch")
    public RestResponse<ShortLinkBatchCreateRespDTO> batchCreateShortLink(@RequestBody ShortLinkBatchCreateReqDTO requestParam) {
        return RestResponse.success(shortLinkService.batchCreateShortLink(requestParam));
    }

    /**
     * 修改短链接
     */
    @PostMapping("/api/short-link/v1/update")
    public RestResponse<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkService.updateShortLink(requestParam);
        return RestResponse.success();
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/api/short-link/v1/page")
    public RestResponse<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return RestResponse.success(shortLinkService.pageShortLink(requestParam));
    }

}
