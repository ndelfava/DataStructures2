
import matplotlib.pyplot as plt
import numpy as np

# Sample data for a simple line
x = np.array([1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000])
yMax = np.array([1, 3, 4, 8, 8, 11, 13, 15, 17, 18])
yAve = np.array([1, 2.4, 3.1, 5.58, 6.48, 8.68, 9.7, 11.9, 12.9, 14.4])

# Basic line plot
plt.plot(x, yMax)
plt.plot(x, yAve)

# Customize appearance (optional)
plt.xlabel('X-axis')
plt.ylabel('Y-axis')
plt.title('My Line Graph')

# Show the plot
plt.show()
