package com.example.boardservice.domain.board.dto.response;


import com.example.boardservice.domain.board.entity.Board;
import com.example.boardservice.domain.board.entity.BoardImage;
import com.example.boardservice.global.client.dto.MemberInfoByMemberIdResponseDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ReadBoardListResponseDto {
    private Long memberId;
    private String memberEmail;
    private String nickname;
    private String profile;
    private Long boardId;
    private String title;
    private String content;
    private String createTime;
    private String updateTime;
    private List<String> imagePath;
    private Long boardLikeCount;
    private Long boardCommentCount;
    private List<BoardListWithComment> comments=new ArrayList<>();

    @Builder
    public ReadBoardListResponseDto(Board board, MemberInfoByMemberIdResponseDto memberInfoByMemberIdResponseDto) {
        this.memberEmail=memberInfoByMemberIdResponseDto.getEmail();
        this.memberId =memberInfoByMemberIdResponseDto.getId();
        this.nickname = memberInfoByMemberIdResponseDto.getNickname();
        this.profile = memberInfoByMemberIdResponseDto.getProfile();
        this.boardId = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createTime = String.valueOf(board.getCreatedAt());
        this.updateTime= String.valueOf(board.getUpdatedAt())
        this.imagePath = board.getBoardImages().stream().map(BoardImage::getImage).collect(Collectors.toList());
        this.boardLikeCount=board.getLike().getLikeCount();
        this.boardCommentCount=board.getBoardCommentCount().getBoardCommentCount();
        this.comments = board.getBoardComments().stream().map(boardComment -> new BoardListWithComment(boardComment,memberInfoByMemberIdResponseDto)).collect(Collectors.toList());
    }
}
