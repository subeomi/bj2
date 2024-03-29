package org.lsb.bj2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.lsb.bj2.dto.PageRequestDTO;
import org.lsb.bj2.dto.PageResponseDTO;
import org.lsb.bj2.dto.ReplyDTO;
import org.lsb.bj2.mappers.ReplyMapper;
import org.lsb.bj2.mappers.TodoMapper;

import groovy.util.logging.Log4j2;
import lombok.RequiredArgsConstructor;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
  
  private final ReplyMapper replyMapper;
  private final TodoMapper todoMapper;
  @Override
  public Long register(ReplyDTO replyDTO) {

    Long result = null;
    Long gno = replyDTO.getGno();

    if(gno == 0L){
      int count = replyMapper.insert(replyDTO);

      if(count != 1){
        throw new RuntimeException("Reply Insert Exception");
      }

      Long rno = replyDTO.getRno();
      replyMapper.updateGno(rno);
      result = rno;
    }else {
      int count = replyMapper.insertChild(replyDTO);

      if(count != 1){
        throw new RuntimeException("Reply Insert Exception");
      }
      result = replyDTO.getRno();
    }

    return result;
  }

  @Override
  public PageResponseDTO<ReplyDTO> getList(Long tno, PageRequestDTO pageRequestDTO){

    pageRequestDTO.setSize(100);

    List<ReplyDTO> list = replyMapper.selectList((tno), pageRequestDTO);
    int total = replyMapper.getTnoCount(tno);

    return PageResponseDTO.<ReplyDTO>withAll()
    .list(list)
    .total(total)
    .build();
  }

  @Override
  public ReplyDTO getOne(Long tno) {
    
    return replyMapper.selectOne(tno);
  }

}
