package com.qf.endOneKm.feign;


import com.qf.endOneKm.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient("admin-server")
public interface AdminMFeign {

    @GetMapping("/admin/list")
    public R list();

    @GetMapping("/admin/test")
    public R test();

    @GetMapping("/admin/test2")
    public R test2();

}
