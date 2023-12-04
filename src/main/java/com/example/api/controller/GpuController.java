package com.example.api.controller;

import com.example.api.dto.GpuDto;
import com.example.api.model.Gpu;
import com.example.api.service.GpuService;
import com.example.api.views.GpuForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gpus")
@RequiredArgsConstructor
public class GpuController {

    private final GpuService gpuService;

    @GetMapping
    public String gpuList(Model model) {
        var gpus = gpuService.getAllGpus();
        model.addAttribute("gpus", gpus);
        return "gpu-list";
    }

    @GetMapping("/create")
    public String createGpuForm(Model model) {
        var gpus = gpuService.getAllGpus();
//        model.addAttribute("gpus", gpus);
        model.addAttribute("gpuForm", new GpuForm());
        return "gpu-form";
    }

    @GetMapping("/edit/{name}")
    public String editGpuForm(Model model, @PathVariable String name) {
//        var gpus = gpuService.getAllGpus();
        var gpu = gpuService.getGpu(name);
        model.addAttribute("gpuForm", new GpuForm(gpu));
        return "gpu-form";
    }

    @PostMapping("/create")
    public String createGpu(@ModelAttribute("gpuForm") GpuForm gpuForm) {
        var gpu = createGpuFromGpuForm(gpuForm);
        gpuService.createGpu(gpu);
        return "redirect:/gpus";
    }

    @PutMapping("/update/{name}")
    public String updateGpu(@PathVariable String name, @ModelAttribute("gpuForm") GpuForm gpuForm) {
//        gpuForm.setName(name);
        var gpu = createGpuFromGpuForm(gpuForm);
//        gpu.setName(name);
        gpuService.updateGpu(name, gpu);
        return "redirect:/gpus";
    }

    @DeleteMapping("/delete/{name}")
    public String deleteGpu(@PathVariable String name) {
        gpuService.deleteGpu(name);
        return "redirect:/gpus";
    }

    @DeleteMapping("delete/all")
    public String deleteAllGpus() {
        gpuService.deleteAllGpus();
        return "redirect:/gpus";
    }

    private GpuDto createGpuFromGpuForm(GpuForm gpuForm) {
        return new GpuDto(
                gpuForm.getName(),
                gpuForm.getVideoRamCapacity()
        );
    }

//    private GpuForm createGpuFormFromGpu(Gpu gpu) {
//        return GpuForm.builder()
//                .name(gpu.getName())
//                .videoRamCapacity(gpu.getVideoRamCapacity())
//                .build();
//    }

//    @GetMapping
//    ResponseEntity<List<GpuDto>> getAllRams() {
//        List<GpuDto> gpuDtos = gpuService.getAllGpus();
//        return ResponseEntity.ok(gpuDtos);
//    }
//
//    @GetMapping("/{name}")
//    ResponseEntity<GpuDto> getRam(@PathVariable(name = "name") String name) {
//        GpuDto gpuDto = gpuService.getGpu(name);
//        return ResponseEntity.ok(gpuDto);
//    }
//
//    @PutMapping("/{name}")
//    ResponseEntity<Void> updateRam(@PathVariable(name = "name") String name,@RequestBody @Valid GpuDto gpuDto) {
//        gpuService.updateGpu(name, gpuDto);
//        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
//    }
//
//    @PostMapping
//    ResponseEntity<Void> createRam(@RequestBody @Valid GpuDto gpuDto) {
//        gpuService.createGpu(gpuDto);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @DeleteMapping("/{name}")
//    ResponseEntity<Void> deleteRam(@PathVariable(name = "name") String name) {
//        gpuService.deleteGpu(name);
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("/all")
//    ResponseEntity<Void> deleteAllRams() {
//        gpuService.deleteAllGpus();
//        return ResponseEntity.noContent().build();
//    }
}
