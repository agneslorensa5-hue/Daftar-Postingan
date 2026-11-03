
package com.example.daftarpostingan

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.daftarpostingan.data.Post
import com.example.daftarpostingan.network.RestClient
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: PostAdapter
    private lateinit var tvStatus: TextView
    private lateinit var progress: ProgressBar
    private lateinit var btnUlang: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvStatus = findViewById(R.id.tvStatus)
        progress = findViewById(R.id.progress)
        btnUlang = findViewById(R.id.btnUlang)

        val recyclerView =
            findViewById<RecyclerView>(R.id.recyclerPost)

        // Mengatur tampilan daftar postingan
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Membuat adapter
        adapter = PostAdapter(
            daftar = mutableListOf(),

            // Klik biasa untuk melihat detail
            onKlik = { post ->
                tampilkanDetail(post)
            },

            // Tekan lama untuk menampilkan konfirmasi hapus
            onHapus = { posisi ->
                if (posisi in 0 until adapter.itemCount) {
                    val post = adapter.ambil(posisi)
                    konfirmasiHapus(post)
                }
            }
        )

        recyclerView.adapter = adapter

        // Tombol memuat ulang
        btnUlang.setOnClickListener {
            muatPosts()
        }

        // Mengambil data saat aplikasi dibuka
        muatPosts()
    }

    // Mengambil data postingan dari API
    private fun muatPosts() {

        progress.visibility = View.VISIBLE
        btnUlang.visibility = View.GONE
        tvStatus.text = "Memuat postingan..."

        lifecycleScope.launch {
            try {
                val hasil = RestClient.api.getPosts(limit = 20)

                adapter.tampilkan(hasil)

                tvStatus.text =
                    "${hasil.size} postingan dimuat"

            } catch (e: HttpException) {

                tvStatus.text =
                    "Server error: HTTP ${e.code()}"

                btnUlang.visibility = View.VISIBLE

            } catch (e: IOException) {

                tvStatus.text =
                    "Tidak ada koneksi internet"

                btnUlang.visibility = View.VISIBLE

            } finally {
                progress.visibility = View.GONE
            }
        }
    }

    // Menampilkan detail postingan ketika diklik
    private fun tampilkanDetail(post: Post) {
        val konten = KontenPostingan.ambil(post.id)

        AlertDialog.Builder(this)
            .setTitle(konten.judul)
            .setMessage(
                getString(
                    R.string.detail_post,
                    konten.isi,
                    post.id,
                    post.userId
                )
            )
            .setPositiveButton("TUTUP", null)
            .show()
    }

    // Menampilkan konfirmasi sebelum menghapus
    private fun konfirmasiHapus(post: Post) {

        AlertDialog.Builder(this)
            .setTitle("Hapus postingan?")
            .setMessage("Postingan #${post.id} akan dihapus.")
            .setNegativeButton("BATAL", null)
            .setPositiveButton("HAPUS") { _, _ ->
                hapusPost(post)
            }
            .show()
    }

    // Menghapus postingan melalui DELETE API
    private fun hapusPost(post: Post) {

        lifecycleScope.launch {
            try {
                val respons =
                    RestClient.api.hapusPost(post.id)

                if (respons.isSuccessful) {

                    // Mencari posisi terbaru postingan
                    val posisi = adapter.posisiDari(post)

                    if (posisi != -1) {
                        adapter.hapusPada(posisi)
                    }

                    Toast.makeText(
                        this@MainActivity,
                        "Postingan #${post.id} dihapus",
                        Toast.LENGTH_SHORT
                    ).show()

                    tvStatus.text =
                        "${adapter.itemCount} postingan ditampilkan"

                } else {

                    Toast.makeText(
                        this@MainActivity,
                        "Gagal menghapus: HTTP ${respons.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: IOException) {

                Toast.makeText(
                    this@MainActivity,
                    "Tidak ada koneksi internet",
                    Toast.LENGTH_SHORT
                ).show()

            } catch (e: Exception) {

                Toast.makeText(
                    this@MainActivity,
                    "Gagal menghapus postingan",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
