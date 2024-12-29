package com.project.mailscheduler.controller;


import com.project.mailscheduler.dto.ScheduledMailCreateRequestDto;
import com.project.mailscheduler.dto.ScheduledMailGetResponseDto;
import com.project.mailscheduler.dto.ScheduledMailUpdateRequestDto;
import com.project.mailscheduler.service.ScheduledMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scheduled-mails")
public class ScheduledJobController {

    @Autowired
    private ScheduledMailService scheduledMailService;



    @GetMapping("/all")
    public ResponseEntity<List<ScheduledMailGetResponseDto>> getAllScheduledMails(){
        List<ScheduledMailGetResponseDto> allMails = scheduledMailService.getAllScheduledMails();
        return ResponseEntity.status(HttpStatus.OK).body(allMails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduledMailGetResponseDto>  getScheduledMail(@PathVariable(name = "id") Long scheduleId ){
        ScheduledMailGetResponseDto responseDto = scheduledMailService.getScheduledMailById(scheduleId);
        return ResponseEntity.ok(responseDto);

    }

    //create a new schedule controller
    //create new service, create a request dto, response dto already in place
    // write a basic exception if error in creation
    @PostMapping(value = "/newschedule", consumes = {"application/json","text/html"})
    public ResponseEntity<ScheduledMailGetResponseDto> createSchedule(@RequestBody ScheduledMailCreateRequestDto newscheduledmail){

        return ResponseEntity.ok(scheduledMailService.createSchedule(newscheduledmail));
    }

    @DeleteMapping("/deleteschedule/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable(name="id") Long scheduleId){
        return ResponseEntity.status(HttpStatus.OK).body(scheduledMailService.deleteScheduledMail(scheduleId));
    }
    @PutMapping(value = "/updateschedule/{id}",consumes = {"application/json"})
    public ResponseEntity<ScheduledMailGetResponseDto> updateSchedule(@RequestBody ScheduledMailUpdateRequestDto updateScheduledMail,
                                                                      @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(scheduledMailService.updateScheduledMail(id, updateScheduledMail));
    }
}
