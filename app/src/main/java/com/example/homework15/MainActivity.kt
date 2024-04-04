package com.example.homework15

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homework15.Data.App
import com.example.homework15.Data.DictionaryDao
import com.example.homework15.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/*

Что нужно сделать
1. Напишите мини-приложение для подсчёта слов. Интерфейс должен позволять добавлять в словарь новое слово,
просматривать список первых слов в словаре, искать количество совпадений, а также очищать данные приложения.
2. Создайте базу данных, используя Room.
3. В качестве модели необходимо объявить сущность, содержащую слово (оно же используется в качестве ключа) и количество его повторений.

7. Добавьте проверку на ввод слова. Необходимо блокировать добавление пустых строк и слов, содержащих пробелы,
цифры, точки и запятые (допустимы только буквы и дефисы). При попытке пользователя добавить такое сочетание выводите соответствующее сообщение на экран.
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel:MainViewModel by viewModels{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val dictionaryDao: DictionaryDao = (application as App).db.dictionaryDao()
                return MainViewModel(dictionaryDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.addBtn.setOnClickListener {
            val text = binding.textInput.text.toString()

            if (text.isNotBlank()){
                viewModel.add(text)
            }else{
                Toast.makeText(this, "Ошибка!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.clearBtn.setOnClickListener {
            viewModel.delete()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.allWord.collect{word ->
                    binding.text.text = word.joinToString(
                        separator = "\r\n"
                    )
                }
            }
        }
    }

     fun isValid(text: String): Boolean {
        return text.matches(Regex("""[A-Za-z-]"""))
    }

}