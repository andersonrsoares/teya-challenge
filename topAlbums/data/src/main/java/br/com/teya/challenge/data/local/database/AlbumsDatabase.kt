package br.com.teya.challenge.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.teya.challenge.data.local.database.entities.AlbumEntity
import br.com.teya.challenge.data.model.AlbumEntry

@Database(
    entities = [
        AlbumEntity::class,
    ],
    exportSchema = false,
    version = 22,
)
internal abstract class AlbumsDatabase : RoomDatabase() {
    abstract fun albumsDao(): AlbumsDao

    internal companion object {

        fun newInstance(context: Context) =
            Room
                .databaseBuilder(
                    context,
                    AlbumsDatabase::class.java,
                    "album-db",
                ).fallbackToDestructiveMigration(true)
                .build()
    }
}
