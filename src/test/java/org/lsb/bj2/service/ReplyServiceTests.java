package org.lsb.bj2.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.lsb.bj2.dto.PageRequestDTO;
import org.lsb.bj2.dto.ReplyDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {
  
  @Autowired
  private ReplyService replyService;

  @Test
  public void testRegister(){

    ReplyDTO replyDTO = ReplyDTO.builder()
    .tno(100L)
    .reply("댓글 테스트")
    .replyer("replyer00")
    .build();

    replyService.register(replyDTO);

  }

    @Test
  public void testRegister2(){

    ReplyDTO replyDTO = ReplyDTO.builder()
    .tno(100L)
    .gno(14L)
    .reply("댓글 테스트")
    .replyer("replyer00")
    .build();

    log.info(replyService.register(replyDTO));

  }

  @Test
  public void testList() {

    log.info(replyService.getList(100L, 
      PageRequestDTO.builder().build()));

  }

}