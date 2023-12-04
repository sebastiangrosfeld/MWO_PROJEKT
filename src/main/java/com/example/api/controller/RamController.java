package com.example.api.controller;

import com.example.api.dto.ComputerDto;
import com.example.api.dto.RamDto;
import com.example.api.model.Ram;
import com.example.api.service.ComputerService;
import com.example.api.service.RamService;
import com.example.api.views.RamForm;
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
@RequestMapping("/rams")
@RequiredArgsConstructor
public class RamController {

    private final RamService ramService;

    @GetMapping
    public String ramList(Model model) {
        var rams = ramService.getAllRams();
        model.addAttribute("rams", rams);
        return "ram-list";
    }

    @GetMapping("/create")
    public String createRamForm(Model model) {
        var rams = ramService.getAllRams();
//        model.addAttribute("gpus", gpus);
        model.addAttribute("ramForm", new RamForm());
        return "ram-form";
    }

    @GetMapping("/edit/{name}")
    public String editRamForm(Model model, @PathVariable String name) {
//        var gpus = gpuService.getAllGpus();
        var ram = ramService.getRam(name);
        model.addAttribute("ramForm", new RamForm(ram));
        return "ram-form";
    }

    @PostMapping("/create")
    public String createRam(@ModelAttribute("ramForm") RamForm ramForm) {
        var ram = createRamFromRamForm(ramForm);
        ramService.createRam(ram);
        return "redirect:/rams";
    }

    @PutMapping("/update/{name}")
    public String updateRam(@PathVariable String name, @ModelAttribute("ramForm") RamForm ramForm) {
//        ramForm.setName(name);
        var ram = createRamFromRamForm(ramForm);
//        ram.setName(name);
        ramService.updateRam(name, ram);
        return "redirect:/rams";
    }

    @DeleteMapping("/delete/{name}")
    public String deleteRam(@PathVariable String name) {
        ramService.deleteRam(name);
        return "redirect:/rams";
    }

    @DeleteMapping("delete/all")
    public String deleteAllRams() {
        ramService.deleteAllRams();
        return "redirect:/rams";
    }

    private RamDto createRamFromRamForm(RamForm ramForm) {
        return new RamDto(
                ramForm.getName(),
                ramForm.getRamCapacity(),
                ramForm.getMemoryRate()
        );
    }

//    private RamForm createRamFormFromRam(Ram ram) {
//        return RamForm.builder()
//                .name(ram.getName())
//                .ramCapacity(ram.getRamCapacity())
//                .memoryRate(ram.getMemoryRate())
//                .build();
//    }

//    @GetMapping
//    ResponseEntity<List<RamDto>> getAllRams() {
//        List<RamDto> ramDtos = ramService.getAllRams();
//        return ResponseEntity.ok(ramDtos);
//    }
//
//    @GetMapping("/{name}")
//    ResponseEntity<RamDto> getRam(@PathVariable(name = "name") String name) {
//        RamDto ramDto = ramService.getRam(name);
//        return ResponseEntity.ok(ramDto);
//    }
//
//    @PutMapping("/{name}")
//    ResponseEntity<Void> updateRam(@PathVariable(name = "name") String name,@RequestBody @Valid RamDto ramDto) {
//        ramService.updateRam(name, ramDto);
//        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
//    }
//
//    @PostMapping
//    ResponseEntity<Void> createRam(@RequestBody @Valid RamDto ramDto) {
//        ramService.createRam(ramDto);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @DeleteMapping("/{name}")
//    ResponseEntity<Void> deleteRam(@PathVariable(name = "name") String name) {
//        ramService.deleteRam(name);
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("/all")
//    ResponseEntity<Void> deleteAllRams() {
//        ramService.deleteAllRams();
//        return ResponseEntity.noContent().build();
//    }
}
