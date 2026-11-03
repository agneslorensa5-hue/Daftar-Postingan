package com.example.daftarpostingan

data class IsiPostingan(
    val judul: String,
    val isi: String
)

object KontenPostingan {

    private val daftar = listOf(
        IsiPostingan(
            "Tips Menjaga Kesehatan",
            "Menjaga kesehatan tubuh dapat dilakukan dengan mengonsumsi makanan bergizi, berolahraga secara rutin, minum air putih yang cukup, dan tidur dengan teratur."
        ),
        IsiPostingan(
            "Pentingnya Pendidikan",
            "Pendidikan memiliki peran penting dalam kehidupan karena dapat membantu seseorang mengembangkan pengetahuan, keterampilan, dan kemampuan berpikir kritis."
        ),
        IsiPostingan(
            "Menjaga Kebersihan Lingkungan",
            "Lingkungan yang bersih menciptakan suasana yang nyaman dan sehat. Kita dapat menjaganya dengan membuang sampah pada tempatnya dan melakukan kerja bakti."
        ),
        IsiPostingan(
            "Manfaat Membaca Buku",
            "Membaca buku dapat menambah wawasan, memperkaya kosakata, meningkatkan konsentrasi, dan melatih kemampuan seseorang dalam memahami informasi."
        ),
        IsiPostingan(
            "Mengatur Waktu dengan Baik",
            "Mengatur waktu membantu kita menyelesaikan berbagai kegiatan secara teratur. Buatlah daftar prioritas agar pekerjaan dan waktu istirahat tetap seimbang."
        ),
        IsiPostingan(
            "Pentingnya Menabung",
            "Menabung merupakan kebiasaan baik untuk mempersiapkan kebutuhan di masa depan. Sisihkan sebagian uang secara rutin sesuai dengan kemampuan."
        ),
        IsiPostingan(
            "Bijak Menggunakan Media Sosial",
            "Media sosial dapat menjadi sarana berbagi informasi dan berkomunikasi. Gunakan dengan bijak serta hindari menyebarkan berita yang belum terbukti kebenarannya."
        ),
        IsiPostingan(
            "Manfaat Berolahraga",
            "Olahraga secara rutin dapat menjaga kebugaran tubuh, meningkatkan daya tahan, memperkuat otot, dan membantu menjaga kesehatan mental."
        ),
        IsiPostingan(
            "Menjaga Kesehatan Mental",
            "Kesehatan mental sama pentingnya dengan kesehatan fisik. Luangkan waktu untuk beristirahat, melakukan hobi, dan berbicara dengan orang yang dipercaya."
        ),
        IsiPostingan(
            "Pentingnya Sarapan Pagi",
            "Sarapan memberikan energi untuk memulai aktivitas. Pilih makanan bergizi seperti telur, buah, sayuran, dan sumber karbohidrat."
        ),
        IsiPostingan(
            "Mengurangi Sampah Plastik",
            "Penggunaan plastik sekali pakai dapat dikurangi dengan membawa botol minum sendiri, menggunakan tas belanja kain, dan memilih produk ramah lingkungan."
        ),
        IsiPostingan(
            "Belajar Teknologi Digital",
            "Kemampuan menggunakan teknologi digital semakin dibutuhkan. Pelajari berbagai aplikasi dan manfaatkan internet untuk memperoleh pengetahuan baru."
        ),
        IsiPostingan(
            "Pentingnya Kerja Sama",
            "Kerja sama membuat suatu pekerjaan menjadi lebih ringan. Dengan saling menghargai pendapat dan membagi tugas, tujuan bersama dapat dicapai."
        ),
        IsiPostingan(
            "Menjaga Keamanan Data Pribadi",
            "Jangan membagikan kata sandi atau informasi pribadi kepada orang lain. Gunakan kata sandi yang kuat dan aktifkan verifikasi dua langkah."
        ),
        IsiPostingan(
            "Manfaat Minum Air Putih",
            "Tubuh membutuhkan air agar dapat berfungsi dengan baik. Biasakan minum air putih secara cukup, terutama setelah beraktivitas."
        ),
        IsiPostingan(
            "Mengembangkan Kreativitas",
            "Kreativitas dapat dikembangkan dengan mencoba hal baru, membaca, berdiskusi, dan tidak takut melakukan kesalahan saat belajar."
        ),
        IsiPostingan(
            "Pentingnya Disiplin",
            "Disiplin membantu seseorang menjadi lebih bertanggung jawab. Mulailah dari kebiasaan sederhana seperti datang tepat waktu dan menyelesaikan tugas."
        ),
        IsiPostingan(
            "Menghargai Perbedaan",
            "Setiap orang memiliki latar belakang dan pendapat yang berbeda. Sikap saling menghargai dapat menciptakan lingkungan yang damai dan harmonis."
        ),
        IsiPostingan(
            "Menjaga Kelestarian Alam",
            "Kelestarian alam dapat dijaga dengan menanam pohon, menghemat energi, mengurangi pencemaran, dan tidak merusak lingkungan sekitar."
        ),
        IsiPostingan(
            "Semangat Meraih Cita-Cita",
            "Meraih cita-cita membutuhkan usaha, kesabaran, dan konsistensi. Tetapkan tujuan yang ingin dicapai dan terus belajar dari setiap pengalaman."
        )
    )

    fun ambil(id: Int): IsiPostingan {
        return daftar.getOrElse(id - 1) {
            IsiPostingan(
                "Postingan Tidak Ditemukan",
                "Konten postingan belum tersedia."
            )
        }
    }
}
