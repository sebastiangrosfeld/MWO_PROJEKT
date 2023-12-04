package com.example.api.controller;

import com.example.api.dto.ComputerDto;
import com.example.api.service.ComputerService;
import com.example.api.service.GpuService;
import com.example.api.service.RamService;
import com.example.api.views.ComputerForm;
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
@RequestMapping("/computers")
@RequiredArgsConstructor
public class ComputerController {

    private final ComputerService computerService;
    private final GpuService gpuService;
    private final RamService ramService;

    @GetMapping
    public String computerList(Model model) {
        var computers = computerService.getAllComputers();
        model.addAttribute("computers", computers);
        return "computer-list";
    }

    @GetMapping("/create")
    public String createComputerForm(Model model) {
        var gpus = gpuService.getAllGpus();
        var rams = ramService.getAllRams();
        model.addAttribute("gpus", gpus);
        model.addAttribute("rams", rams);
        model.addAttribute("computerForm", new ComputerForm());
        return "computer-form";
    }

    @GetMapping("/edit/{name}")
    public String editComputerForm(Model model, @PathVariable String name) {
        var gpus = gpuService.getAllGpus();
        var rams = ramService.getAllRams();
        var computer = computerService.getComputer(name);
        model.addAttribute("gpus", gpus);
        model.addAttribute("rams", rams);
        model.addAttribute("computerForm",new ComputerForm(computer));
        return "computer-form";
    }

    @PostMapping("/create")
    public String createComputer(@ModelAttribute("computerForm") ComputerForm computerForm) {
        var computer = createComputerFromComputerForm(computerForm);
        computerService.createComputer(computer);
        return "redirect:/computers";
    }

    @PutMapping("/update/{name}")
    public String updateComputer(@PathVariable String name, @ModelAttribute("computerForm") ComputerForm computerForm) {
       // computerForm.setName(name);
        var computer = createComputerFromComputerForm(computerForm);
        computerService.updateComputer(name, computer);
        return "redirect:/computers";
    }

    @DeleteMapping("/delete/{name}")
    public String deleteComputer(@PathVariable String name) {
        computerService.deleteComputer(name);
        return "redirect:/computers";
    }

    @DeleteMapping("delete/all")
    public String deleteAllComputers() {
        computerService.deleteAllComputers();
        return "redirect:/computers";
    }

//    private ComputerForm createComputerFormFromComputer(Computer computer) {
//        return ComputerForm.builder()
//                .name(computer.getName())
//                .type(computer.getType())
//                .enclosureName(computer.getEnclosureName())
//                .cpuName(computer.getCpuName())
//                .gpuName(computer.getGpu().getName())
//                .hardDiskCapacity(computer.getHardDiskCapacity())
//                .ramName(computer.getRam().getName())
//                .powerSupply(computer.getPowerSupply())
//                .price(computer.getPrice())
//                .build();
//    }

    private ComputerDto createComputerFromComputerForm(ComputerForm computerForm) {
        var gpu = gpuService.getGpu(computerForm.getGpuName());
        var ram = ramService.getRam(computerForm.getRamName());
        return new ComputerDto(
                computerForm.getName(),
                computerForm.getType(),
                computerForm.getEnclosureName(),
                computerForm.getCpuName(),
                ram,
                computerForm.getHardDiskCapacity(),
                gpu,
                computerForm.getPowerSupply(),
                computerForm.getPrice()
        );
    }

//    @GetMapping
//    ResponseEntity<List<ComputerDto>> getAllComputers() {
//        List<ComputerDto> computerDtos = computerService.getAllComputers();
//        return ResponseEntity.ok(computerDtos);
//    }
//
//    @GetMapping("/{name}")
//    ResponseEntity<ComputerDto> getComputer(@PathVariable(name = "name") String name) {
//        ComputerDto computerDto = computerService.getComputer(name);
//        return ResponseEntity.ok(computerDto);
//    }
//
//    @PutMapping ("/{name}")
//    ResponseEntity<?> updateComputer(@PathVariable(name = "name") String name,@RequestBody @Valid ComputerDto computerDto) {
//        computerService.updateComputer(name, computerDto);
//        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
//    }
//
//    @PostMapping
//    ResponseEntity<Void> createComputer(@RequestBody @Valid ComputerDto computerDto) {
//        computerService.createComputer(computerDto);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @DeleteMapping("/{name}")
//    ResponseEntity<Void> deleteComputer(@PathVariable(name = "name") String name) {
//        computerService.deleteComputer(name);
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("/all")
//    ResponseEntity<Void> deleteAllComputers() {
//        computerService.deleteAllComputers();
//        return ResponseEntity.noContent().build();
//    }
}
