package br.com.teya.challenge.topAlbums.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.teya.challenge.topAlbums.data.local.database.entities.AlbumEntity

@Dao
internal interface AlbumsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(albums: List<AlbumEntity>)

    @Query("SELECT * FROM AlbumEntity where id = :id ")
    suspend fun fetchAlbum(id: String): AlbumEntity?
}