package com.example.demo.dto;

import java.util.List;

public class MemberSelectionDTO {
	  private Long memberId;

    private List<Long> artistIds;
    private List<Long> genreIds;

    public MemberSelectionDTO() {
    }

  
    public MemberSelectionDTO(List<Long> artistIds, List<Long> genreIds) {
        this.artistIds = artistIds;
        this.genreIds = genreIds;
    }

 
    public List<Long> getArtistIds() {
        return artistIds;
    }

    public void setArtistIds(List<Long> artistIds) {
        this.artistIds = artistIds;
    }

    public List<Long> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
    }


	public Long getMemberId() {
		return memberId;
	}


	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
}
