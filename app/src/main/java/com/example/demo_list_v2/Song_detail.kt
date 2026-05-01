package com.example.demo_list_v2

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.io.IOException

private const val ARG_SONG = "song"

class Song_detail : Fragment() {
    private var song: Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            song = it.getSerializable(ARG_SONG) as? Song
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_song_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val tvTitulo = view.findViewById<TextView>(R.id.tvDetalleTitulo)
        val tvArtista = view.findViewById<TextView>(R.id.tvDetalleArtista)
        val tvAlbum = view.findViewById<TextView>(R.id.tvDetalleAlbum)
        val tvGenero = view.findViewById<TextView>(R.id.tvDetalleGenero)
        val tvDuracion = view.findViewById<TextView>(R.id.tvDetalleDuracion)
        val tvFecha = view.findViewById<TextView>(R.id.tvDetalleFecha)
        val ivCover = view.findViewById<ImageView>(R.id.ivDetalleCover)

        song?.let {
            tvTitulo.text = it.titulo
            tvArtista.text = it.artista
            tvAlbum.text = it.album
            tvGenero.text = it.genero
            tvDuracion.text = it.duracion
            tvFecha.text = "Lanzamiento: ${it.fecha_lanzamiento}"
            
            // Carga de la portada
            try {
                val inputStream = requireContext().assets.open("Songs/${it.portada}")
                val bitmap = BitmapFactory.decodeStream(inputStream)
                ivCover.setImageBitmap(bitmap)
            } catch (e: IOException) {
                ivCover.setImageResource(R.drawable.portada)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(song: Song) =
            Song_detail().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_SONG, song)
                }
            }
    }
}