from ZeroDivisionTk import DivisionUI

class DivisionApp:
    def __init__(self, master):
        self.ui = DivisionUI(master)
        self.ui.divide_button.config(command=self.divide)  
    
    def divide(self):
        try:
            dividend = self.ui.get_dividend()
            divisor = self.ui.get_divisor()

            if divisor == 0:
                raise ZeroDivisionError

            result = dividend / divisor
            self.ui.show_result(result)
        except ValueError:
            self.ui.show_error("Por favor ingrese números válidos.")
        except ZeroDivisionError:
            self.ui.show_error("No se puede dividir por cero.")


if __name__ == "__main__":
    import tkinter as tk

    root = tk.Tk()
    app = DivisionApp(root)
    root.mainloop()
