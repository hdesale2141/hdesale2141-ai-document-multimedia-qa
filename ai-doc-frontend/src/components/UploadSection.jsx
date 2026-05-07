import { useState } from "react";
import API from "../services/api";

const UploadSection = ({ setUploadedFile }) => {

    const [file, setFile] = useState(null);

    const [loading, setLoading] = useState(false);

    const handleUpload = async () => {

        if (!file) return;

        try {

            setLoading(true);

            const formData = new FormData();

            formData.append("file", file);

            const response = await API.post(
                "/files/upload",
                formData,
                {
                    headers: {
                        "Content-Type": "multipart/form-data"
                    }
                }
            );

            setUploadedFile(response.data);

            alert("File uploaded successfully!");

        } catch (error) {

            console.error(error);

            alert("Upload failed");
        } finally {

            setLoading(false);
        }
    };

    return (
        <div className="bg-white p-6 rounded-xl shadow-md">

            <h2 className="text-2xl font-bold mb-4">
                Upload File
            </h2>

            <input
                type="file"
                onChange={(e) => setFile(e.target.files[0])}
                className="mb-4"
            />

            <button
                onClick={handleUpload}
                
                className="bg-blue-600 hover:bg-blue-700 transition text-white px-5 py-3 rounded-xl font-semibold"
            >
                {
                    loading
                        ? "Uploading..."
                        : "Upload"
                }
            </button>

        </div>
    );
};

export default UploadSection;