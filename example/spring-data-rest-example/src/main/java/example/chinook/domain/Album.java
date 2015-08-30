/**
	* Copyright (c) minuteproject, minuteproject@gmail.com
	* All rights reserved.
	* 
	* Licensed under the Apache License, Version 2.0 (the "License")
	* you may not use this file except in compliance with the License.
	* You may obtain a copy of the License at
	* 
	* http://www.apache.org/licenses/LICENSE-2.0
	* 
	* Unless required by applicable law or agreed to in writing, software
	* distributed under the License is distributed on an "AS IS" BASIS,
	* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	* See the License for the specific language governing permissions and
	* limitations under the License.
	* 
	* More information on minuteproject:
	* twitter @minuteproject
	* wiki http://minuteproject.wikispaces.com 
	* blog http://minuteproject.blogspot.net
	* 
*/
/**
	* template reference : 
	* - Minuteproject version : 0.9.5
	* - name      : DomainEntityJPA2Annotation
	* - file name : DomainEntityJPA2Annotation.vm
	* - time      : 2015/08/30 n. Chr. at 09:45:11 MESZ
*/
package example.chinook.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * <p>Title: Album</p>
 *
 * <p>Description: Domain Object describing a Album entity</p>
 *
 */
@Entity (name="Album")
@Table (name="\"album\"")
@NamedQueries ({
	 @NamedQuery(name="Album.findAll", query="SELECT a FROM Album a")
	,@NamedQuery(name="Album.findByTitle", query="SELECT a FROM Album a WHERE a.title = :title")
	,@NamedQuery(name="Album.findByTitleContaining", query="SELECT a FROM Album a WHERE a.title like :title")

})

public class Album implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Album.findAll";
    public static final String FIND_BY_TITLE = "Album.findByTitle";
    public static final String FIND_BY_TITLE_CONTAINING ="Album.findByTitleContaining";
	
    @Id @Column(name="AlbumId" ) 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer albumid;

//MP-MANAGED-ADDED-AREA-BEGINNING @Title-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @Title-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-Title@
    @Column(name="Title"  , length=160 , nullable=false , unique=false)
    private String title; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="ArtistId", referencedColumnName = "ArtistId" , nullable=false , unique=false , insertable=true, updatable=true) 
    private Artist artistid;  

    @Column(name="ArtistId"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer artistid_;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @trackAlbumViaAlbumid-field-album@
    @OneToMany (targetEntity=example.chinook.domain.Track.class, fetch=FetchType.LAZY, mappedBy="albumid", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Track> trackAlbumViaAlbumid = new HashSet<Track>(); 

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public Album() {
    }

	/**
	* All field constructor 
	*/
    public Album(
       Integer albumid,
       String title,
       Integer artistid) {
	 this(
       albumid,
       title,
       artistid
	 ,true);
	}
    
	public Album(
       Integer albumid,
       String title,
       Integer artistid	
    , boolean setRelationship) {
       //primary keys
       setAlbumid (albumid);
       //attributes
       setTitle (title);
       //parents
       if (setRelationship) this.artistid = new Artist();
       if (setRelationship) this.artistid.setArtistid(artistid); 
	   setArtistid_ (artistid);
    }

	public Album flat() {
	   return new Album(
          getAlbumid(),
          getTitle(),
          getArtistid_()
       , false
	   );
	}

    public Integer getAlbumid() {
        return albumid;
    }
	
    public void setAlbumid (Integer albumid) {
        this.albumid =  albumid;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-Title@
    public String getTitle() {
        return title;
    }
	
    public void setTitle (String title) {
        this.title =  title;
    }
	
//MP-MANAGED-UPDATABLE-ENDING


    public Artist getArtistid () {
    	return artistid;
    }
	
    public void setArtistid (Artist artistid) {
    	this.artistid = artistid;
    }

    public Integer getArtistid_() {
        return artistid_;
    }
	
    public void setArtistid_ (Integer artistid) {
        this.artistid_ =  artistid;
    }
	

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @trackAlbumViaAlbumid-getter-album@
    public Set<Track> getTrackAlbumViaAlbumid() {
        if (trackAlbumViaAlbumid == null){
            trackAlbumViaAlbumid = new HashSet<Track>();
        }
        return trackAlbumViaAlbumid;
    }

    public void setTrackAlbumViaAlbumid (Set<Track> trackAlbumViaAlbumid) {
        this.trackAlbumViaAlbumid = trackAlbumViaAlbumid;
    }	
    
    public void addTrackAlbumViaAlbumid (Track element) {
    	    getTrackAlbumViaAlbumid().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
